package ru.gb.sklyarov.shop.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gb.sklyarov.shop.entities.Statistic;

@Getter
@Setter
@NoArgsConstructor
public class StatisticDto {
    private String serviceName;
    private long duration;

    public StatisticDto(Statistic statistic){
        this.serviceName = statistic.getService_name();
        this.duration = statistic.getDuration();
    }
}
