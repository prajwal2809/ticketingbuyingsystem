package com.example.orderservice.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "total")
    private BigDecimal totalPrice;

    @Column(name = "quantity")
    private Long ticketCount;

    @CreationTimestamp
    @Column(name = "placed_at", updatable = false, nullable = false)
    private LocalDateTime placedAt;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "event_id")
    private Long eventId;

    public Order() {
    }

    public Order(Long ticketCount, Long id, BigDecimal totalPrice, LocalDateTime placedAt, Long customerId, Long eventId) {
        this.ticketCount = ticketCount;
        this.Id = id;
        this.totalPrice = totalPrice;
        this.placedAt = placedAt;
        this.customerId = customerId;
        this.eventId = eventId;
    }

    public static Builder builder() {
        return new Builder();
    }
    // Getters
    public Long getId() {
        return Id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Long getTicketCount() {
        return ticketCount;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getEventId() {
        return eventId;
    }

    // ✅ Static Builder Class
    public static class Builder {
        private Long id;
        private BigDecimal totalPrice;
        private Long ticketCount;
        private LocalDateTime placedAt;
        private Long customerId;
        private Long eventId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder ticketCount(Long ticketCount) {
            this.ticketCount = ticketCount;
            return this;
        }

        public Builder placedAt(LocalDateTime placedAt) {
            this.placedAt = placedAt;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder eventId(Long eventId) {
            this.eventId = eventId;
            return this;
        }

        public Order build() {
            return new Order(ticketCount, id, totalPrice, placedAt, customerId, eventId);
        }
    }
}
