package ru.gb.sklyarov.shop.core.ms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

//    private static final String PREFIX_DB_INDEX = "web_shop_statistics_";
//    private final StatisticRepository statisticRepository;
//
//    public List<Statistic> findAll() {
//        return statisticRepository.findAll();
//    }
//
//    public List<Statistic> findAllStatistics() {
//        return statisticRepository.findAllStatistics();
//    }
//
//    public void save(Statistic statistic) {
//        statisticRepository.save(statistic);
//    }
//
//    // REDIS
//
//    public void saveToRedis(Statistic statistic) {
//        String key = PREFIX_DB_INDEX + statistic.getService_name();
//        if (getStatisticsInRedis(key) == null) {
////            redisTemplate.opsForValue().set(key, statistic);
//            return;
//        }
//        updateStatisticsInRedis(statistic);
//    }
//
//    public Statistic getStatisticsInRedis(String key) {
//        return (Statistic) redisTemplate.opsForValue().get(key);
//    }
//
//    public void updateStatisticsInRedis(Statistic statistics) {
//        String key = PREFIX_DB_INDEX + statistics.getService_name();
//        Statistic currentStatistic = getStatisticsInRedis(key);
//        currentStatistic.setDuration(currentStatistic.getDuration() + statistics.getDuration());
//        redisTemplate.opsForValue().set(key, currentStatistic);
//    }
//
//    public List<Statistic> findAllStatisticsInRedis() {
//        String key = PREFIX_DB_INDEX + "*";
//        Set keys = redisTemplate.keys(key);
//        if (CollectionUtils.isEmpty(keys)) {
//            return Collections.emptyList();
//        }
//        return redisTemplate.opsForValue().multiGet(keys);
//
//    }
}
