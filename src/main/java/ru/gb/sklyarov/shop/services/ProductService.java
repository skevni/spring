package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.cart.CartsContent;
import ru.gb.sklyarov.shop.models.Product;
import ru.gb.sklyarov.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CartService cartService;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllProductsByPrice(Double minPriceLimit, Double maxPriceLimit) {
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

    public void addProductToCart(CartsContent cartsContent){
        cartService.add(cartsContent);
    }
}
