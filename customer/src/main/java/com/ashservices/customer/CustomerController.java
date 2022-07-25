package com.ashservices.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer( @RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);

    }

    @GetMapping("id/{id}")
    public Optional<Customer> getCustomer( @PathVariable Integer id) {
        log.info(" Customer Query is received{} ", id);
        Optional<Customer> customer = customerService.queryCustomerById(id);
        System.out.println(customer);
        return customer;
    }

    @GetMapping("/all")
    public List<Customer> getCustomer() {
        log.info(" All Customer Query is received ");
        List<Customer> customer = customerService.queryAllCustomers();
        System.out.println(customer);
        return customer;
    }

    @GetMapping("/name/{firstName}")
    public Optional<Customer> getCustomer( @PathVariable String firstName) {
        log.info(" Customer Query is received{} ", firstName);
        Optional<Customer> customer = customerService.queryCustomerByFirstName(firstName);
        System.out.println(customer);
        return customer;
    }

    @PutMapping("/{id}")
    public Customer replaceCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
        log.info(" Put request having customer replace is called for " + customer + " Id :" + id);
        return customerService.replaceCustomer(customer, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id ) {
        log.info(" Delete request having customer id is received {} ", id);
        customerService.deleteCustomer(id);
    }




}
