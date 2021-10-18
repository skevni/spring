package ru.gb.sklyarov.shop.core.ms.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.gb.sklyarov.shop.common.dtos.CartDto;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.cart-service.url}")
    private String cartServiceUrl;

    public CartDto getUserCart(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("ERROR!!!");
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", principal.getName());
        return restTemplate.exchange(cartServiceUrl, HttpMethod.GET, new HttpEntity<>(headers), CartDto.class).getBody();
    }

    public void clear(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("ERROR!!!");
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", principal.getName());
        restTemplate.exchange(cartServiceUrl + "/clear", HttpMethod.GET, new HttpEntity<>(headers), void.class);
    }
}
