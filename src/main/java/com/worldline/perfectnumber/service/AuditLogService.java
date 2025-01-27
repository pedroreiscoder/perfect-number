package com.worldline.perfectnumber.service;

import com.worldline.perfectnumber.entity.AuditLog;
import com.worldline.perfectnumber.repository.AuditLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditLogService(AuditLogRepository auditLogRepository){
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional
    public void logRequest(String ipAddress, List<Integer> perfectNumbers){

        String joinedList = perfectNumbers.stream().map(String::valueOf).collect(Collectors.joining(","));

        log.info("IP Address: {}; Perfect Numbers: {}", ipAddress, joinedList);

        AuditLog auditLog = AuditLog.builder()
                .ipAddress(ipAddress)
                .perfectNumbers(joinedList)
                .build();

        auditLogRepository.save(auditLog);
    }
}
