package ru.gb.sklyarov.shop.core.ms.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.sklyarov.shop.core.ms.services.StatisticsService;
import ru.gb.sklyarov.shop.core.ms.utils.EntityConverter;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticsService statisticsService;
    private final EntityConverter converter;

//    @GetMapping
//    public List<StatisticDto> getStatistics() {
////        return statisticsService.findAllStatistics().stream().map(StatisticDto::new).collect(Collectors.toList());
////        return statisticsService.findAllStatisticsInRedis().stream().map(converter::statisticToDto).collect(Collectors.toList());
//    }
}
