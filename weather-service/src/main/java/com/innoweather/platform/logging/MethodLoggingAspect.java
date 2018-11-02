package com.innoweather.platform.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodLoggingAspect {

    @Before("bean(*Client) || bean(*Handler) || bean(*Service) || bean(*Controller)")
    public void logMethodEntry(JoinPoint joinPoint) {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.debug("Method {} invoked with arguments {}" + joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "bean(*Client) || bean(*Handler) || bean(*Service) || bean(*Controller)", returning = "retValue")
    public void logMethodExit(JoinPoint joinPoint, Object retValue) {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.debug("Method {} exited with return value {}", joinPoint.getSignature(), retValue);
    }

}
