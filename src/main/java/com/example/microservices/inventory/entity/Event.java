package com.example.microservices.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="event")
public class Event {

    @Id
    private Long id;

    public void setLeftCapacity(Long leftCapacity) {
        this.leftCapacity = leftCapacity;
    }

    @Column(name = "name")
    private String name;
    @Column(name = "total_capacity")
    private Long totalCapacity;
    @Column(name = "left_capacity")
    private Long leftCapacity;
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;


    @Column(name="ticket_price")
    private BigDecimal ticketPrice;

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getTotalCapacity() {
        return totalCapacity;
    }

    public Long getLeftCapacity() {
        return leftCapacity;
    }

    public Venue getVenue() {
        return venue;
    }
}
