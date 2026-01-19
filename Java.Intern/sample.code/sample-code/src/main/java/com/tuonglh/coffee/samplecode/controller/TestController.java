package com.tuonglh.coffee.samplecode.controller;

import com.tuonglh.coffee.samplecode.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final AuditService auditService;

    @Autowired
    public TestController(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * Test endpoint to verify MongoDB connection.
     * Call this to save a test audit log.
     */
    @GetMapping("/audit-log")
    public ResponseEntity<String> testAuditLog() {
        auditService.log(
                "TEST_ACTION",
                "test_user",
                Map.of(
                        "message", "Testing MongoDB integration",
                        "timestamp", System.currentTimeMillis()));
        return ResponseEntity.ok("Audit log saved successfully! Check MongoDB.");
    }
}
