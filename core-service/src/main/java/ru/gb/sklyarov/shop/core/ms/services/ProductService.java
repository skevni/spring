package ru.gb.sklyarov.shop.core.ms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.sklyarov.shop.common.dtos.*;
import ru.gb.sklyarov.shop.common.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.core.ms.entities.Comment;
import ru.gb.sklyarov.shop.core.ms.entities.Product;
import ru.gb.sklyarov.shop.core.ms.repositories.ProductRepository;
import ru.gb.sklyarov.shop.core.ms.ws.products.ProductWs;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CommentService commentService;

    private final WebClient authServiceWebClient;
    private final WebClient orderServiceWebClient;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllProductsByPrice(BigDecimal minPriceLimit, BigDecimal maxPriceLimit) {
        if (minPriceLimit == null && maxPriceLimit == null) {
            return new ArrayList<>();
        }
        if (minPriceLimit != null && maxPriceLimit != null) {
            return productRepository.findAllByPriceBetween(minPriceLimit, maxPriceLimit);
        }

        if (minPriceLimit != null) {
            return productRepository.findAllByPriceIsGreaterThanEqual(minPriceLimit);
        }

        return productRepository.findAllByPriceIsLessThanEqual(maxPriceLimit);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

//    SOAP

    public Optional<ProductWs> findByIdSoap(Long id) {
        return productRepository.findById(id).map(this::productToProductWs);
    }

    public List<ProductWs> findAllSoap() {
        return productRepository.findAll().stream().map(this::productToProductWs).collect(Collectors.toList());
    }

    private ProductWs productToProductWs(Product product) {
        ProductWs productWs = new ProductWs();
        productWs.setId(product.getId());
        productWs.setTitle(product.getTitle());
        productWs.setPrice(product.getPrice());
        return productWs;
    }
//    public static final Function<Product, ProductWs> productToProductWs = product ->{
//        ProductWs productWs = new ProductWs();
//        productWs.setId(product.getId());
//        productWs.setTitle(product.getTitle());
//        productWs.setPrice(product.getPrice());
//        return productWs;
//    };

    public List<Comment> findCommentsByUserAndProduct(String username, Long productId) {
        UserDto userDto = authServiceWebClient.get().uri("/users/" + username).retrieve().bodyToMono(UserDto.class).block();
        if (userDto == null) {
            return Collections.emptyList();
        }
        return commentService.findCommentsByUserIdAndProduct(userDto.getUserId(),
                productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product id: " + productId + " not found in the repository!")));
    }

    public List<Comment> findCommentsByProduct(Long productId) {
        return commentService.findCommentsByProduct(productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product id: " + productId + " not found in the repository!")));
    }

    public void saveComment(CommentDto commentDto, String username) {
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setProduct(productRepository.findById(commentDto.getProduct_id()).orElseThrow(() -> new ResourceNotFoundException("Product ID: " + commentDto.getProduct_id() + " not found")));
        UserDto userDto = authServiceWebClient.get().uri("/users/" + username).retrieve().bodyToMono(UserDto.class).block();
        if (userDto != null) {
            comment.setUserId(userDto.getUserId());
            commentService.save(comment);
        }
    }

    public boolean findPurchase(Long productId, String username) {
        if (username == null) {
            return false;
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", username);
        List<OrderDto> orderList = orderServiceWebClient.get()
                .uri("/api/v1/orders/" + productId)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<OrderDto>>() {
        }).block();
        if (orderList != null && !orderList.isEmpty()) {
            for (OrderDto o: orderList){
                if (o.getCartItems().stream().anyMatch(orderItemDto -> orderItemDto.getProduct_id().equals(productId))){
                    return true;
                }
            }
        }
        return false;
    }
}
