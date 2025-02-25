package com.reservation.logaspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReservationLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.reservation.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
    	logger.info("Entering method: {}", joinPoint.getSignature().getName());
    	if (logger.isInfoEnabled()) {
    	    logger.info("Arguments: {}", Arrays.toString(joinPoint.getArgs()));
    	}
   }


    @AfterReturning(pointcut = "execution(* com.reservation.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
    	logger.info("Exiting method: {}", joinPoint.getSignature().getName());
        logger.info("Return value: {}", result);
    }


    @AfterThrowing(pointcut = "execution(* com.reservation.service.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
    	logger.error("Exception in method: {}", joinPoint.getSignature().getName());
        logger.error("Exception: ", error);
    }

}