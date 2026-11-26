package com.site.customer_service.service;

import com.site.customer_service.client.OrderClient;
import com.site.customer_service.dto.CustomerOrdersDTO;
import com.site.customer_service.dto.OrderDTO;
import com.site.customer_service.model.Customer;
import com.site.customer_service.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderClient orderClient;

    public CustomerService(
            CustomerRepository customerRepository,
            OrderClient orderClient) {

        this.customerRepository = customerRepository;
        this.orderClient = orderClient;
    }

    // CREATE
    public Customer createCustomer(Customer customer) {
        customer.setId(null);
        return customerRepository.save(customer);
    }

    // READ ALL
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // READ BY ID
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Customer updateCustomer(String id, Customer customer) {
        Customer existingCustomer =
                customerRepository.findById(id).orElse(null);

        if (existingCustomer == null) {
            return null;
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    // DELETE
    public void deleteCustomer(String id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
    }

    // CUSTOMER + ORDERS (RESTCLIENT)
    public CustomerOrdersDTO getCustomerWithOrders(String customerId) {

        Customer customer =
                customerRepository.findById(customerId).orElse(null);

        if (customer == null) {
            return null;
        }

        List<OrderDTO> orders =
                orderClient.getOrdersByCustomerId(customerId);

        return new CustomerOrdersDTO(customer, orders);
    }
}
