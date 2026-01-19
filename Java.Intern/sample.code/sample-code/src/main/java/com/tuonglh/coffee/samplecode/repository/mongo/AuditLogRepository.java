package com.tuonglh.coffee.samplecode.repository.mongo;

import com.tuonglh.coffee.samplecode.entity.mongo.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {
    List<AuditLog> findByUsername(String username);

    List<AuditLog> findByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
