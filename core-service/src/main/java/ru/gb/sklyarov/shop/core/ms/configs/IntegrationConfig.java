package ru.gb.sklyarov.shop.core.ms.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Component
public class IntegrationConfig {
    @Value("${integration.auth-service.url}")
    private String authServerUrl;

    @Value("${integration.order-service.url}")
    private String orderServiceUrl;

    @Bean
    public WebClient authServiceWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5_000)
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(10_000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5_000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl(authServerUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    public WebClient orderServiceWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
                .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(5_000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5_000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl(orderServiceUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
