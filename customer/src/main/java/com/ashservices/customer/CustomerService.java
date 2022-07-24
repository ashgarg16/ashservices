package com.ashservices.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService{

    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName( request.lastName())
                .email(request.email())
                .build();

        //todo: check if email is valid
        //todo : check if email is not taken
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8082/api/v1/fraud/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }


        
    }

    public String queryCustomer(QueryCustomer queryCustomer) {

        List<Customer> customers =customerRepository.findAll();

        System.out.println(customers);
        return customerRepository.findAll().toString();

    }
}
