package com.ashservices.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService{

    private final CustomerRepository customerRepository;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName( request.lastName())
                .email(request.email())
                .build();

        //todo: check if email is valid
        //todo : check if email is not taken

        customerRepository.save(customer);
        
    }

    public String queryCustomer(QueryCustomer queryCustomer) {

        List<Customer> customers =customerRepository.findAll();

        System.out.println(customers);
        return customerRepository.findAll().toString();

    }
}
