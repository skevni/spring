package ru.gb.sklyarov.shop.core.ms.aspects;

import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;

//@Aspect
//@Component
@RequiredArgsConstructor
public class ServiceAspect {
    // TODO переделать на хранение статистики в памяти, а не в БД (redis в моем случае)
//    private final StatisticsService statisticsService;
//
//    //    @Around("execution(public * ru.gb.sklyarov.shop.services.*.*(..))")
//    public Object collectingStatistics(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//        Object out = joinPoint.proceed();
//        long finish = System.currentTimeMillis();
//        long duration = finish - start;
//        System.out.println("===== Method \"" + joinPoint.getTarget().getClass().getSimpleName() + "\" duration: " + duration);
//
//        String serviceName = joinPoint.getTarget().getClass().getSimpleName();
//        if (!serviceName.equals("StatisticsService")) {
//            Statistic statistic = new Statistic();
//            statistic.setService_name(joinPoint.getTarget().getClass().getSimpleName());
//            statistic.setDuration(duration);
////            statisticsService.save(statistic);
//            statisticsService.saveToRedis(statistic);
//        }
//
//        return out;
//    }


}
