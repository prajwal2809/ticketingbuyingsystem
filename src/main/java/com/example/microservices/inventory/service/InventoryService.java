package com.example.microservices.inventory.service;


import com.example.microservices.inventory.entity.Event;
import com.example.microservices.inventory.entity.Venue;
import com.example.microservices.inventory.repository.EventRepository;
import com.example.microservices.inventory.repository.VenueRepository;
import com.example.microservices.inventory.response.EventInventoryResponse;
import com.example.microservices.inventory.response.VenueInventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public  InventoryService(final EventRepository eventRepository,final VenueRepository venueRepository){
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }
    public List<EventInventoryResponse> getAllEvents(){

        final List<Event> events = eventRepository.findAll();


        return events.stream().map(event -> EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .build()).collect(Collectors.toList());

    }

    public VenueInventoryResponse getVenueInformation(Long venueId) {

        final Venue venue = venueRepository.findById(venueId).orElse(null);
        return VenueInventoryResponse.builder().
                venueId(venue.getId()).
                venueName(venue.getName()).
                totalCapacity(venue.getTotalCapacity()).
                build();
    }
}
