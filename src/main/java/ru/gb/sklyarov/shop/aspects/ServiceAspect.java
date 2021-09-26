package ru.gb.sklyarov.shop.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.entities.Statistic;
import ru.gb.sklyarov.shop.services.StatisticsService;

@Aspect
@Component
@RequiredArgsConstructor
public class ServiceAspect {
    private final StatisticsService statisticsService;

    @Around("execution(public * ru.gb.sklyarov.shop.services.*.*(..))")
    public Object collectingStatistics(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object out = joinPoint.proceed();
        long finish = System.currentTimeMillis();
        long duration = finish - start;
        System.out.println("===== Method \"" + joinPoint.getTarget().getClass().getSimpleName() + "\" duration: " + duration);

        String serviceName = joinPoint.getTarget().getClass().getSimpleName();
        if (!serviceName.equals("StatisticsService")) {
            Statistic statistic = new Statistic();
            statistic.setService_name(joinPoint.getTarget().getClass().getSimpleName());
            statistic.setDuration(duration);
//            statisticsService.save(statistic);
            statisticsService.saveToRedis(statistic);
        }

        return out;
    }


}
