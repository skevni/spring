package ru.gb.sklyarov.shop.order.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.sklyarov.shop.common.dtos.CartDto;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient webUserServiceClient;

    // при использовании RestTemplate для получения корзины пользователя на вебинаре указывали адрес сервиса корзины без URI,
// но корзину мы отдаем по endpoit = /api/v1/{uuid}
// Если пожно пояснить еще раз, как мы получаем в таком случае пользовтаельскую корзину, если в uri не указываем обязательный
// путь /{uuid}
    public CartDto getUserCart(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("ERROR!!!");
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", principal.getName());
        return webUserServiceClient.get()
                .uri("/api/v1/" + principal.getName())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve().bodyToMono(CartDto.class)
                .block();
    }

    public void clear(String username) {
        if (username == null) {
            throw new RuntimeException("ERROR!!!");
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", username);
        // не передаю cartId, Т.к. очистка производится только корзины аутентифицированного пользователя
        webUserServiceClient.get()
                .uri("/api/v1/cart/clear")
                .headers(httpHeaders -> httpHeaders.addAll(headers));
//        restTemplate.exchange(cartServiceUrl + "/api/v1/cart/clear", HttpMethod.GET, new HttpEntity<>(headers), void.class);
    }
}
