package com.ashservices.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
