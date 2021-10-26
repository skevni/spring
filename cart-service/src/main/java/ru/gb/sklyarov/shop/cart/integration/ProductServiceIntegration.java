package ru.gb.sklyarov.shop.cart.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long productId) {
//        ProductDto productDto = restTemplate.getForObject(productServiceUrl + "/api/v1/products/" + productId, ProductDto.class);
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }
}