package com.tuonglh.coffee.samplecode.service;

import com.tuonglh.coffee.samplecode.entity.mongo.AuditLog;
import com.tuonglh.coffee.samplecode.repository.mongo.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    /**
     * Saves an audit log asynchronously.
     * 
     * @param action   The action performed (e.g., "LOGIN", "UPDATE_ORDER")
     * @param username The user who performed the action
     * @param details  Additional details about the action
     */
    @Async
    public void log(String action, String username, Map<String, Object> details) {
        AuditLog auditLog = new AuditLog(action, username, details);
        auditLogRepository.save(auditLog);
    }
}
