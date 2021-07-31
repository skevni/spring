package ru.gb.sklyarov.shop.repositories;

import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init(){
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L,"Shoes",30.89),
                new Product(2L,"Shirt", 12.99),
                new Product(3L,"Jacket", 100.78),
                new Product(4L,"Coat", 150.99)
        ));
    }
    public List<Product> showAll(){
        return Collections.unmodifiableList(products);
    }

    public Product findById(Long id){
        return products.stream().filter(product -> product.getId().equals(id) ).findFirst().orElse(null);
    }

    public void addProduct(Product product){
        products.add(product);
    }
}
