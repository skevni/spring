package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.sklyarov.shop.dtos.StatisticDto;
import ru.gb.sklyarov.shop.entities.Statistic;
import ru.gb.sklyarov.shop.services.StatisticsService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticsService statisticsService;

    @GetMapping
    public List<StatisticDto> getStatistics() {
//        return statisticsService.findAllStatistics().stream().map(StatisticDto::new).collect(Collectors.toList());
        return statisticsService.findAllStatisticsInRedis().stream().map(StatisticDto::new).collect(Collectors.toList());
    }
}
