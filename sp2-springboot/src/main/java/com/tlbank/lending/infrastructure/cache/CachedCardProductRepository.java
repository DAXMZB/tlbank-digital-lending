package com.tlbank.lending.infrastructure.cache;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.tlbank.lending.domain.application.CardProductId;
import com.tlbank.lending.domain.product.CardProduct;
import com.tlbank.lending.domain.product.repository.CardProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Caching decorator around the JPA-backed {@link CardProductRepository}.
 */
@Primary
@Component
@Slf4j
@RequiredArgsConstructor
public class CachedCardProductRepository implements CardProductRepository {

    private final @Qualifier("cardProductRepositoryImpl") CardProductRepository delegate;
    private final CacheStore cacheStore;
    private final CacheTtlProvider cacheTtlProvider;

    @Override
    public List<CardProduct> findAllEnabled() {
        Optional<List<CardProduct>> cached = cacheStore.get(CacheKeys.CARD_PRODUCT_ALL);
        if (cached.isPresent()) {
            log.debug("Cache HIT for card products: {}", CacheKeys.CARD_PRODUCT_ALL);
            return cached.get();
        }

        log.debug("Cache MISS for card products: {}", CacheKeys.CARD_PRODUCT_ALL);
        List<CardProduct> products = delegate.findAllEnabled();
        cacheStore.put(CacheKeys.CARD_PRODUCT_ALL, products, cacheTtlProvider.getTtlSeconds());
        return products;
    }

    @Override
    public Optional<CardProduct> findById(CardProductId id) {
        String cacheKey = CacheKeys.cardProductKey(id.value());
        Optional<CardProduct> cached = cacheStore.get(cacheKey);
        if (cached.isPresent()) {
            log.debug("Cache HIT for card product: {}", cacheKey);
            return cached;
        }

        log.debug("Cache MISS for card product: {}", cacheKey);
        Optional<CardProduct> product = delegate.findById(id);
        product.ifPresent(value -> cacheStore.put(cacheKey, value, cacheTtlProvider.getTtlSeconds()));
        return product;
    }

    /**
     * Evicts all cached card product entries and reloads them from the database.
     */
    public int refreshCache() {
        int evicted = evictCardProductKeys();
        List<CardProduct> products = delegate.findAllEnabled();
        cacheStore.put(CacheKeys.CARD_PRODUCT_ALL, products, cacheTtlProvider.getTtlSeconds());
        for (CardProduct product : products) {
            cacheStore.put(
                    CacheKeys.cardProductKey(product.getProductId().value()),
                    product,
                    cacheTtlProvider.getTtlSeconds());
        }
        return evicted + products.size();
    }

    private int evictCardProductKeys() {
        int evicted = 0;
        for (String key : cacheStore.keys()) {
            if (key.equals(CacheKeys.CARD_PRODUCT_ALL) || key.startsWith(CacheKeys.CARD_PRODUCT_PREFIX)) {
                cacheStore.evict(key);
                evicted++;
            }
        }
        return evicted;
    }
}
