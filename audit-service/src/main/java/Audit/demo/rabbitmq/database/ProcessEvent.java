package Audit.demo.rabbitmq.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "processed_events")
public class ProcessEvent {
        @Id
        private  String eventId;

    public LocalDateTime getProcessAt() {
        return processAt;
    }

    public void setProcessAt(LocalDateTime processAt) {
        this.processAt = processAt;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    private LocalDateTime processAt;
    public ProcessEvent() {
    }

    public ProcessEvent(
            String eventId,
            LocalDateTime processedAt
    ) {
        this.eventId = eventId;
        this.processAt = processedAt;
    }
}
