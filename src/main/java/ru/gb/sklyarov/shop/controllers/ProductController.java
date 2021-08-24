package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.dtos.ProductDto;
import ru.gb.sklyarov.shop.models.Product;
import ru.gb.sklyarov.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10)
                .map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id));
    }

    @GetMapping("/filter")
    public List<ProductDto> getProductByFilter(@RequestParam(name = "min_price", required = false) Double minPriceLimit, @RequestParam(name = "max_price", required = false) Double maxPriceLimit) {
        return productService.findAllProductsByPrice(minPriceLimit, maxPriceLimit)
                .stream().map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.findById(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productService.save(product);
        return new ProductDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllProducts() {
        productService.deleteAll();
    }

}
