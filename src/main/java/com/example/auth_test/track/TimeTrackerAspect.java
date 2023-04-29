package com.example.auth_test.track;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeTrackerAspect {
    @Around("@annotation(TrackTime)")
    public Object trackTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("{} executed in {} ms", proceedingJoinPoint.getSignature(), duration);
        return result;
    }
}
