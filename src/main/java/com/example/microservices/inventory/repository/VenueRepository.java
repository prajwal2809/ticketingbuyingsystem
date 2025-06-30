package com.example.microservices.inventory.repository;

import com.example.microservices.inventory.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue,Long> {
}
