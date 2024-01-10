package com.fransica.cardscheme.aspect;

import com.fransica.cardscheme.entity.AuditLog;
import com.fransica.cardscheme.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class CardVerificationAspect {

    private final AuditLogRepository auditLogRepository;

    @After("@annotation(org.springframework.web.bind.annotation.GetMapping) && execution(* com.fransica.cardscheme.controller.CardDetailsController.verifyCardData(..)) && args(bin)")
    public void afterVerifyCardData(JoinPoint joinPoint, String bin) {
        log.info("Monitoring: Request for bin " + bin);
        Optional<AuditLog> auditLogOptional = auditLogRepository.findAuditLogByBin(bin);
        if (auditLogOptional.isPresent()) {
            AuditLog auditLog = auditLogOptional.get();
            auditLog.setCount(auditLog.getCount() + 1);
           auditLogRepository.save(auditLog);
        } else {
            AuditLog newLog = AuditLog.builder()
                    .bin(bin)
                    .count(1L)
                    .build();
            auditLogRepository.save(newLog);
        }
    }
}