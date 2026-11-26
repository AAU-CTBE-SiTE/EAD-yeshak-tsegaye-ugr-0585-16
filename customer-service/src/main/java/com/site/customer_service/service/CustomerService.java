package com.site.customer_service.service;

import com.site.customer_service.model.Customer;
import com.site.customer_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        customer.setId(null);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(String id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);

        if (existingCustomer == null) {
            return null;
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(String id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
    }
}
