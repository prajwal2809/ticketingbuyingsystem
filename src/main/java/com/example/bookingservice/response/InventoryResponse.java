package com.example.bookingservice.response;


import java.math.BigDecimal;

public class InventoryResponse {

    private Long eventId;
    private String event;
    private Long capacity;

    public Long getEventId() {
        return eventId;
    }

    public String getEvent() {
        return event;
    }

    public Long getCapacity() {
        return capacity;
    }

    public VenueResponse getVenue() {
        return venue;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    private VenueResponse venue;
    private BigDecimal ticketPrice;

}
