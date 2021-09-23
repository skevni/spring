package ru.gb.sklyarov.shop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
    @Around("execution(public * ru.gb.sklyarov.shop.services.*.*(..))")
    public Object collectingStatistics(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object out = joinPoint.proceed();
        long finish = System.currentTimeMillis();
        long duration = finish - start;
        System.out.println("===== Method \"" + joinPoint.getSignature() + "\" duration: " + duration);

        return out;
    }
}
