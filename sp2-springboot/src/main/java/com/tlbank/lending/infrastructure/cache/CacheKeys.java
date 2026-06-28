package com.tlbank.lending.infrastructure.cache;

/**
 * Well-known cache key prefixes and identifiers.
 */
public final class CacheKeys {

    public static final String SYSTEM_PARAM_PREFIX = "sys_param:";
    public static final String CARD_PRODUCT_ALL = "card_products:all";
    public static final String CARD_PRODUCT_PREFIX = "card_product:";

    private CacheKeys() {
    }

    public static String systemParamKey(String group, String key) {
        return SYSTEM_PARAM_PREFIX + group + ":" + key;
    }

    public static String cardProductKey(String productId) {
        return CARD_PRODUCT_PREFIX + productId;
    }
}
