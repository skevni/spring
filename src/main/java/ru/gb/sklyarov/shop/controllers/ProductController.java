package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.gb.sklyarov.shop.dtos.ProductDto;
import ru.gb.sklyarov.shop.models.Product;
import ru.gb.sklyarov.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public Page<ProductDto> getAllProducts(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10)
                .map(ProductDto::new);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id));
    }

    @PostMapping("/products")
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productService.save(product);
        return new ProductDto(product);
    }

    @GetMapping("/products/delete/{id}")
    public RedirectView deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return new RedirectView("/products");
    }

    @GetMapping("/products/filter")
    public List<ProductDto> getProductByFilter(@RequestParam(name = "min_price", required = false) Double minPriceLimit, @RequestParam(name = "max_price", required = false) Double maxPriceLimit) {
        return productService.findAllProductsByPrice(minPriceLimit, maxPriceLimit)
                .stream().map(ProductDto::new)
                .collect(Collectors.toList());
    }
}
