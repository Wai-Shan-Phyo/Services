package Audit.demo.rabbitmq.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedEventRepository extends JpaRepository<ProcessEvent, String> {
}
