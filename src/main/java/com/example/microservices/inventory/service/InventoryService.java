package com.example.microservices.inventory.service;


import com.example.microservices.inventory.entity.Event;
import com.example.microservices.inventory.entity.Venue;
import com.example.microservices.inventory.repository.EventRepository;
import com.example.microservices.inventory.repository.VenueRepository;
import com.example.microservices.inventory.response.EventInventoryResponse;
import com.example.microservices.inventory.response.VenueInventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    public EventInventoryResponse getEventInventory(final Long eventId){

        final  Event event = eventRepository.findById(eventId).orElse(null);
        return EventInventoryResponse.builder()
                .event(event.getName())
                .ticketPrice(event.getTicketPrice())
                .capacity(event.getLeftCapacity())
                .eventId(event.getId())
                .venue(event.getVenue())
                .build();
    }

    public void updateEventCapacity(final Long eventId,final  Long ticketsBooked){

        final Event event = eventRepository.findById(eventId).orElse(null);

        event.setLeftCapacity(event.getLeftCapacity()-ticketsBooked);

        eventRepository.saveAndFlush(event);
        //log.info("Updated event capacity for event id: {} with tickets booked: {} " , eventId, ticketsBooked);

        System.out.println("Updated event capacity for event id: {} with tickets booked: {} " + eventId + ticketsBooked);
    }


}
