package com.userservice.service.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Integer> {
  List<OutboxEvent> findByStatus(String status);
}
