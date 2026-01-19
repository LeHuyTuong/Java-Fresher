package com.tuonglh.coffee.samplecode.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "audit_logs")
public class AuditLog {
    @Id
    private String id;

    private String action;
    private String username;
    private LocalDateTime timestamp;
    private Map<String, Object> details;

    public AuditLog() {
    }

    public AuditLog(String action, String username, Map<String, Object> details) {
        this.action = action;
        this.username = username;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}
