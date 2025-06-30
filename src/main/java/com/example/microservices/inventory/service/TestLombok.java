package com.example.microservices.inventory.service;

import com.example.microservices.inventory.response.EventInventoryResponse;

public class TestLombok {
    public static void main(String[] args) {
        EventInventoryResponse obj = EventInventoryResponse.builder()
                .event("Test Event")
                .capacity(100L)
                .build();

        System.out.println(obj);
    }
}

