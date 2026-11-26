package com.site.customer_service.dto;

import java.math.BigDecimal;

public record OrderDTO(
        Long id,
        String customerId,
        String productName,
        Integer quantity,
        BigDecimal price
) {
}
