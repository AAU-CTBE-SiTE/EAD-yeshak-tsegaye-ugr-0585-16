package com.site.customer_service.dto;

import com.site.customer_service.model.Customer;
import java.util.List;

public record CustomerOrdersDTO(
        Customer customer,
        List<OrderDTO> orders
) {
}
