package com.example.microservices.inventory.repository;

import com.example.microservices.inventory.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
