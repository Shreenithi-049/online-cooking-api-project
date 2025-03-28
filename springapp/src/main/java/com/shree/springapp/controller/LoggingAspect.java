package com.shree.springapp.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
@Before("execution(* com.example.controller.*.*(..))")
    public void beforeAdvice() {
        System.out.println("Executing before method call...");
    }

    @After("execution(* com.example.controller.*.*(..))")
    public void afterAdvice() {
        System.out.println("Executing after method call...");
    }

    @Around("execution(* com.example.controller.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before method execution: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("After method execution: " + joinPoint.getSignature().getName());
        return result;
    }
}
