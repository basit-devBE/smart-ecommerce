package com.example.smart_ecommerce.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(com.example.smart_ecommerce.controllers..*)")
    public void controllerMethods() {}


    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Entering method: " + joinPoint.getSignature().getName());
    }

    @After("controllerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Exiting method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "controllerMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("Exception in method: " + joinPoint.getSignature().getName() + " with message: " + error.getMessage());
    }



}
