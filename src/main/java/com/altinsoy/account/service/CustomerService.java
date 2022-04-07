package com.altinsoy.account.service;

import com.altinsoy.account.dto.CustomerDto;
import com.altinsoy.account.dto.converter.CustomerDtoConverter;
import com.altinsoy.account.exception.CustomerNotFoundException;
import com.altinsoy.account.model.Customer;
import com.altinsoy.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer couldn't find by id " + id));
    }

    public CustomerDto getCustomerById(String customerId) {
        return converter.convertToCustomerDto(findCustomerById(customerId));
    }
}
