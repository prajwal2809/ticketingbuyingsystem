package com.example.orderservice.service;

import com.example.orderservice.client.InventoryServiceClient;
import com.example.orderservice.entity.Order;
import com.example.bookingservice.event.BookingEvent;


import com.example.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;

    private InventoryServiceClient inventoryServiceClient;
    @Autowired
    public OrderService(OrderRepository orderRepository,InventoryServiceClient inventoryServiceClient){

        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @KafkaListener(topics = "booking", groupId = "order-service")
    public  void orderEvent(@Payload BookingEvent bookingEvent){

        //log.info("Received order event: {}",bookingEvent);

        System.out.println("Consumed event: " + bookingEvent);

        System.out.println("Received order event: {}"+ bookingEvent);
        // create order object for DB

        Order order = createOrder(bookingEvent);

        orderRepository.saveAndFlush(order);


        System.out.println(" after  order create and before inventoryServiceClient ");
        //update inventory
        inventoryServiceClient.updateInventory(order.getEventId(),order.getTicketCount());

        //log.info("Inventory updated for event: {}, less tickets: {}", order.getEventId(),order.getTicketCount());
        System.out.println("Inventory updated for event: {}, less tickets: {}\"," +order.getEventId()+"," +order.getTicketCount());
    }


    private Order createOrder(BookingEvent bookingEvent){

        return  Order.builder()
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();

    }
}

