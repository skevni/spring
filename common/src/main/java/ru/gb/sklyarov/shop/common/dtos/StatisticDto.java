package ru.gb.sklyarov.shop.common.dtos;

public class StatisticDto {
    private String serviceName;
    private long duration;

    public StatisticDto() {
    }

    public StatisticDto(String serviceName, long duration) {
        this.serviceName = serviceName;
        this.duration = duration;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
