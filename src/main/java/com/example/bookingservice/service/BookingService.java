package com.example.bookingservice.service;


import com.example.bookingservice.client.InventoryServiceClient;
import com.example.bookingservice.entity.Customer;
import com.example.bookingservice.event.BookingEvent;
import com.example.bookingservice.repository.CustomerRepository;
import com.example.bookingservice.request.BookingRequest;
import com.example.bookingservice.response.BookingResponse;
import com.example.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingService {


    private final CustomerRepository customerRepository;
    private final  InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String,BookingEvent> kafkaTemplate;

    @Autowired
    public BookingService(final CustomerRepository customerRepository, final  InventoryServiceClient inventoryServiceClient , final KafkaTemplate<String,BookingEvent> kafkaTemplate){
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.kafkaTemplate = kafkaTemplate;

    }

    public BookingResponse createBooking(final BookingRequest bookingRequest){

        // check if user exists

        System.out.println("under create booking");
        final Customer customer = customerRepository.findById(bookingRequest.getUserId()).orElse(null);
        System.out.println("customer id is "+ customer.getId());
        if( customer == null){
            throw new RuntimeException("User not Found");
        }

        // check if there is enough inventory
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(bookingRequest.getEventId());
        System.out.println("Inventory Service Provider --> " + inventoryResponse);

        if ( inventoryResponse.getCapacity() < bookingRequest.getTicketCount()){

            throw new RuntimeException("Not enough inventory");
        }

        // get event information to also get Venue information
        // create booking

        final BookingEvent bookingEvent = createBookingEvent(bookingRequest,customer,inventoryResponse);

        // send booking to order service on a kafka topic

        kafkaTemplate.send("booking",bookingEvent);
       // log.info("Booking sent to Kafka: {}",bookingEvent);
        System.out.println( "under booking event->" +bookingEvent);

        System.out.println("ticker count is "+bookingEvent.getTicketCount());
        System.out.println("total price is " + bookingEvent.getTotalPrice());
        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();

      //      System.out.println(bookingResponse.getTotalPrice());
    }

    private BookingEvent createBookingEvent(final BookingRequest bookingRequest, final Customer customer,final  InventoryResponse inventoryResponse){


        System.out.println( "getting ticket count is " + bookingRequest.getTicketCount());
        System.out.println("getting  ticket price is " + inventoryResponse.getTicketPrice());
        return  BookingEvent.builder()
                .userId(customer.getId())
                .eventId(bookingRequest.getEventId())
                .ticketCount(bookingRequest.getTicketCount())
                .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())))
                .build();
    }
}
