package ru.gb.sklyarov.shop.order.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public void clear(String username) {
        if (username == null) {
            throw new RuntimeException("ERROR!!!");
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", username);
        // не передаю cartId, Т.к. очистка производится только корзины аутентифицированного пользователя
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(void.class)
                .block();
    }
}
