package ru.gb.sklyarov.shop.order.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class IntegrationConfig {
    @Value("${integration.auth-service.url}")
    private String authServiceUrl;

    @Value("${integration.cart-service.url}")
    private String cartServiceUrl;

    @Value("${integration.core-service.url}")
    private String coreServiceUrl;

    @Bean
    public WebClient authServiceWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
                .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(5_000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5_000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl(authServiceUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    public WebClient cartServiceWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
                .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(5_000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5_000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl(cartServiceUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    public WebClient coreServiceWebClient(){
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
                .doOnConnected(connection -> connection.addHandlerFirst(new ReadTimeoutHandler(5_000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5_000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl(coreServiceUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
