package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.dtos.CommentDto;
import ru.gb.sklyarov.shop.dtos.ProductDto;
import ru.gb.sklyarov.shop.dtos.StringResponse;
import ru.gb.sklyarov.shop.entities.Product;
import ru.gb.sklyarov.shop.exceptions.DataValidationException;
import ru.gb.sklyarov.shop.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.services.ProductService;

import java.security.Principal;
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
        return productService.findAll(pageIndex - 1, 10).map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product ID: " + id + " not found")));
    }

    @GetMapping("/{id}/info")
    public ProductDto getProductDetailById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product ID: " + id + " not found")));
    }

    @GetMapping("/{id}/comments/my")
    public List<CommentDto> getUsersComments(@PathVariable Long id, Principal principal) {
        return productService.findCommentsByUserAndProduct(principal, id).stream().map(CommentDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getComments(@PathVariable Long id) {
        return productService.findCommentsByProduct(id).stream().map(CommentDto::new).collect(Collectors.toList());
    }

    @PostMapping("/comment")
    public void saveComment(@RequestBody CommentDto commentDto, Principal principal) {
        productService.saveComment(commentDto, principal);
    }

    @GetMapping("/{id}/purchase")
    public StringResponse getInformationAboutPurchase(@PathVariable Long id, Principal principal) {
        return new StringResponse(Boolean.toString(productService.findPurchase(id, principal)));
    }

    @GetMapping("/filter")
    public List<ProductDto> getProductByFilter(@RequestParam(name = "min_price", required = false) Double minPriceLimit, @RequestParam(name = "max_price", required = false) Double maxPriceLimit) {
        return productService.findAllProductsByPrice(minPriceLimit, maxPriceLimit)
                .stream().map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product ID: " + productDto.getId() + " not found"));
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
