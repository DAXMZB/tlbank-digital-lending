package com.tlbank.lending.common.response;

import java.util.List;

/**
 * Paginated response wrapper for list endpoints.
 *
 * @param <T> type of elements in the page
 */
public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
