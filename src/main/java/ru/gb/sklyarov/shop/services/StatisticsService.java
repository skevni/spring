package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.entities.Statistic;
import ru.gb.sklyarov.shop.repositories.StatisticRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticRepository statisticRepository;

    public List<Statistic> findAll() {
        return statisticRepository.findAll();
    }

    public List<Statistic> findAllStatistics() {
        return statisticRepository.findAllStatistics();
    }

    public void save(Statistic statistic) {
        statisticRepository.save(statistic);
    }
}
