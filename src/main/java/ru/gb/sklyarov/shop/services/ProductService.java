package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.models.Product;
import ru.gb.sklyarov.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Long id) {
        return productRepository.getById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.delete(productRepository.getById(id));
    }

    public List<Product> findAllProductsByPrice(Double minPriceLimit, Double maxPriceLimit) {
        if (minPriceLimit == null && maxPriceLimit == null) {
            return new ArrayList<>();
        }
        if (minPriceLimit != null && maxPriceLimit != null) {
            return productRepository.findAllByPriceBetween(minPriceLimit, maxPriceLimit);
        }

        if (minPriceLimit != null ) {
            return productRepository.findAllByPriceIsGreaterThanEqual(minPriceLimit);
        }

        return productRepository.findAllByPriceIsLessThanEqual(maxPriceLimit);
    }
}
