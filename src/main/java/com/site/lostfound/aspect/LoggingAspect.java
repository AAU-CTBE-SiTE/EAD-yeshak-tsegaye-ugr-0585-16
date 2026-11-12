package com.site.lostfound.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.site.lostfound.service.LostItemService.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void before(JoinPoint joinPoint) {
        System.out.println("[BEFORE] " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[SUCCESS] " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("[ERROR] " + joinPoint.getSignature().getName() + ": " + error.getMessage());
    }

    @After("serviceMethods()")
    public void after(JoinPoint joinPoint) {
        System.out.println("[AFTER] " + joinPoint.getSignature().getName());
    }

    @Around("serviceMethods()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long elapsed = (System.nanoTime() - start) / 1_000_000;
            System.out.println("[TIME] " + joinPoint.getSignature().getName() + " took " + elapsed + " ms");
        }
    }
}
