package ru.gb.sklyarov.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.model.Product;
import ru.gb.sklyarov.shop.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> showAll(){
        return productRepository.showAll();
    }

    public Product findByIs(Long id){
        return productRepository.findById(id);
    }

    public void addProduct(Product product){
        productRepository.addProduct(product);
    }

    public void changeCost(Product product, double costDelta){
        productRepository.setCost(product, costDelta);
    }
}
