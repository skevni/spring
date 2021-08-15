package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.gb.sklyarov.shop.dtos.ProductDto;
import ru.gb.sklyarov.shop.models.Product;
import ru.gb.sklyarov.shop.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id));
    }

    @PostMapping("/products")
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(product.getTitle());
        product.setPrice(product.getPrice());
        return new ProductDto(product);
    }

    @GetMapping("/products/delete/{id}")
    public RedirectView deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return new RedirectView("/products");
    }

    @GetMapping("/products/filter")
    public List<Product> getProductByFilter(@RequestParam(name = "min_price", required = false) Double minPriceLimit, @RequestParam(name = "max_price", required = false) Double maxPriceLimit) {
        return productService.findAllProductsByPrice(minPriceLimit, maxPriceLimit);
    }
}
