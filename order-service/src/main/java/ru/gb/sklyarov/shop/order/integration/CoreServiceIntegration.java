package ru.gb.sklyarov.shop.order.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;
import ru.gb.sklyarov.shop.common.dtos.UserDto;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    //    private final RestTemplate restTemplate;
    private final WebClient webCoreServiceClient;

    public ProductDto getProductById(Long productId) {
        return webCoreServiceClient.get()
                .uri("/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
//        return restTemplate.getForObject(coreServiceUrl + "/api/v1/products/" + productId, ProductDto.class);
    }

    public UserDto getUserByUsername(String username) {
        return webCoreServiceClient.get()
                .uri("/users/" + username)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
//        return restTemplate.getForObject(coreServiceUrl + "/users/" + username, UserDto.class);
    }
}