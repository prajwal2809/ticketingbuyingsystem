package com.example.microservices.inventory.response;

import com.example.microservices.inventory.entity.Venue;

public class EventInventoryResponse {
    private String event;
    private Long capacity;
    private Venue venue;

    public EventInventoryResponse() {}

    public EventInventoryResponse(String event, Long capacity, Venue venue) {
        this.event = event;
        this.capacity = capacity;
        this.venue = venue;
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

    // ✅ Manual builder class
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String event;
        private Long capacity;
        private Venue venue;

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
            return new EventInventoryResponse(event, capacity, venue);
        }
    }
}
