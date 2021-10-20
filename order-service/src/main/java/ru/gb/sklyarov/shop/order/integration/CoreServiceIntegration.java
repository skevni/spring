package ru.gb.sklyarov.shop.order.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;
import ru.gb.sklyarov.shop.common.dtos.UserDtoFront;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.core-service.url}")
    private String coreServiceUrl;

    public ProductDto getProductById(Long productId) {
        return restTemplate.getForObject(coreServiceUrl + "/api/v1/products/" + productId, ProductDto.class);
    }

    public UserDtoFront getUserByUsername(String username) {
        return restTemplate.getForObject(coreServiceUrl + "/users/" + username, UserDtoFront.class);
    }
}