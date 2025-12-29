package com.university.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.university.patterns..*(..))")
    public void patternMethods() {}

    @Pointcut("execution(* com.university.services..*(..))")
    public void serviceMethods() {}

    @Around("patternMethods()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        System.out.println("[ASPECT] ENTRY: " + className + "." + methodName + "()");

        if (args.length > 0) {
            System.out.println("[ASPECT] Arguments: " + java.util.Arrays.toString(args));
        }
        try {
            Object result = joinPoint.proceed();
            System.out.println("[ASPECT] EXIT: " + className + "." + methodName + "()");
            return result;
        } catch (Throwable t) {
            System.out.println("[ASPECT] EXCEPTION in " + className + "." + methodName + "(): " + t.getMessage());
            throw t;
        }
    }
    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("[ASPECT] " + className + "." + methodName +
                    "() executed in " + duration + " ms");
        }
    }
}