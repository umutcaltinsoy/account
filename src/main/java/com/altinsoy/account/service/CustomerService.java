package com.altinsoy.account.service;

import com.altinsoy.account.exception.CustomerNotFoundException;
import com.altinsoy.account.model.Customer;
import com.altinsoy.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(
                ()-> new CustomerNotFoundException("Customer couldn't find by id " + id));
    }
}
