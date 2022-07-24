package com.ashservices.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save( FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .build());
        return false;
    }
}
