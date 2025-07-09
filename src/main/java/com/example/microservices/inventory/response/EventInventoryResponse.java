package com.example.microservices.inventory.response;

import com.example.microservices.inventory.entity.Venue;

import java.math.BigDecimal;

public class EventInventoryResponse {
    private String event;
    private Long capacity;
    private Venue venue;
    private Long eventId;
    private BigDecimal ticketPrice;

    public EventInventoryResponse() {}

    public EventInventoryResponse(String event, Long capacity, Venue venue, Long eventId,BigDecimal ticketPrice) {
        this.event = event;
        this.capacity = capacity;
        this.venue = venue;
        this.eventId = eventId;
        this.ticketPrice = ticketPrice;
    }
    public Long getEventId(){
        return eventId;
    }
    public BigDecimal getTicketPrice(){
        return ticketPrice;
    }

    public String getEvent() {
        return event;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void  setEventId(Long eventId){
        this.eventId = eventId;
    }

    public void setTicketPrice(BigDecimal ticketPrice){
        this.ticketPrice = ticketPrice;
    }

    // ✅ Manual builder class
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String event;
        private Long capacity;
        private Venue venue;
        private Long eventId;
        private BigDecimal ticketPrice;


        public Builder eventId(Long eventId){
            this.eventId = eventId;
            return  this;
        }

        public  Builder ticketPrice(BigDecimal ticketPrice){
            this.ticketPrice = ticketPrice;
            return  this;
        }

        public Builder event(String event) {
            this.event = event;
            return this;
        }

        public Builder capacity(Long capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder venue(Venue venue) {
            this.venue = venue;
            return this;
        }

        public EventInventoryResponse build() {
            return new EventInventoryResponse(event, capacity, venue,eventId,ticketPrice);
        }
    }
}
