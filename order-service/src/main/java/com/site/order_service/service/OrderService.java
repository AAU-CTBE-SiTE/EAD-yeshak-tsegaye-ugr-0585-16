package com.site.order_service.service;

import com.site.order_service.model.Order;
import com.site.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // CREATE
    public Order createOrder(Order order) {
        order.setId(null);
        return orderRepository.save(order);
    }

    // READ ALL
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // READ BY ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    // READ ORDERS BY CUSTOMER ID
    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    // UPDATE
    public Order updateOrder(Long id, Order order) {
        Order existingOrder = orderRepository.findById(id).orElse(null);

        if (existingOrder == null) {
            return null;
        }

        existingOrder.setCustomerId(order.getCustomerId());
        existingOrder.setProductName(order.getProductName());
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setPrice(order.getPrice());

        return orderRepository.save(existingOrder);
    }

    // DELETE
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
    }
}
