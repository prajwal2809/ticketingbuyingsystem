package com.example.bookingservice.client;


import com.example.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {

    private final RestTemplate restTemplate;


    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryServiceClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public InventoryResponse getInventory(final  Long eventId){

        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl+"/event/"+eventId,InventoryResponse.class);
    }
}
