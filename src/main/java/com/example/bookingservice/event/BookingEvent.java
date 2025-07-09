package com.example.bookingservice.event;

import java.math.BigDecimal;

public class BookingEvent {


    private Long userId;
    private Long eventId;
    private Long ticketCount;

    public BookingEvent() {
    }

    public BookingEvent(Long userId, Long eventId, Long ticketCount, BigDecimal totalPrice) {
        this.userId = userId;
        this.eventId = eventId;
        this.ticketCount = ticketCount;
        this.totalPrice = totalPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getTicketCount() {
        return ticketCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    private BigDecimal totalPrice;

    // ✅ Builder static method
    public static Builder builder() {
        return new Builder();
    }

    // ✅ Builder class
    public static class Builder {
        private Long userId;
        private Long eventId;
        private Long ticketCount;
        private BigDecimal totalPrice;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder eventId(Long eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder ticketCount(Long ticketCount) {
            this.ticketCount = ticketCount;
            return this;
        }

        public Builder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public BookingEvent build() {
            return new BookingEvent(userId, eventId, ticketCount, totalPrice);
        }
    }
}
