package com.ashservices.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public void getCustomer( @RequestBody QueryCustomer queryCustomer) {
        log.info(" Customer Query is received{} ", queryCustomer);
        String customerList = customerService.queryCustomer(queryCustomer);

        System.out.println(customerList);
    }


}
