package ru.gb.sklyarov.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.dao.ProductDao;
import ru.gb.sklyarov.shop.model.Product;

import java.util.List;

@Service
public class ProductService {

    //    private ProductRepository productRepository;
    private ProductDao productRepository;

    @Autowired
    public ProductService(ProductDao productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByIs(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product) {
        product.setId(productRepository.getMaxId() + 1);
        productRepository.save(product);
    }

    public void changeCost(Product product, double costDelta) {
        productRepository.setCost(product, costDelta);
    }
}
