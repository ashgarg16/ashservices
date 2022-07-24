package com.ashservices.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping ( "/api/v1/fraud")
public class FraudController {


    private  final FraudCheckService fraudCheckService;

    @GetMapping(path= "{customer-id}")
    public FraudCheckResponse isFraudster (@PathVariable("customer-id") Integer customerId ) {

       boolean isFraudulendCustomer = fraudCheckService.isFraudulentCustomer(customerId);
       return new FraudCheckResponse(isFraudulendCustomer);

    }

}
