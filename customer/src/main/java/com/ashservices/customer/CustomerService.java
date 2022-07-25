package com.ashservices.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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
                "http://FRAUD/api/v1/fraud/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }


        
    }

    public Optional<Customer> queryCustomerById(Integer customerId) {
       Optional<Customer> customer = customerRepository.findById(customerId);

       return customer;

    }

    public List<Customer> queryAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> queryCustomerByFirstName(String firstName) {
        return customerRepository.findAll().stream().filter((Customer c) -> c.getFirstName().equals(firstName)).findAny();
    }

    public Customer replaceCustomer(Customer customer, Integer id) {

        customerRepository.findById(id)
                .map( c -> {
                    c.setFirstName(customer.getFirstName());
                    c.setLastName(customer.getLastName());
                    c.setEmail(customer.getEmail());
                    return customerRepository.save(c);
                })
                .orElseGet(() -> {
                    customer.setId(id);
                    return customerRepository.save(customer);
                } );
        return customer;
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
