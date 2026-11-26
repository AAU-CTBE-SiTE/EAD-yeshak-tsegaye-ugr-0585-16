package com.site.customer_service.client;

import com.site.customer_service.dto.OrderDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class OrderClient {

    private final RestClient orderRestClient;

    public OrderClient(RestClient orderRestClient) {
        this.orderRestClient = orderRestClient;
    }

    public List<OrderDTO> getOrdersByCustomerId(String customerId) {
        return orderRestClient.get()
                .uri("/orders/customer/{customerId}", customerId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<OrderDTO>>() {});
    }
}
