package ru.gb.sklyarov.shop.core.ms.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.common.dtos.CommentDto;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;
import ru.gb.sklyarov.shop.common.dtos.StringResponse;
import ru.gb.sklyarov.shop.common.exceptions.DataValidationException;
import ru.gb.sklyarov.shop.common.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.core.ms.entities.Product;
import ru.gb.sklyarov.shop.core.ms.services.ProductService;
import ru.gb.sklyarov.shop.core.ms.utils.EntityConverter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final EntityConverter converter;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10).map(converter::productToDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return converter.productToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product ID: " + id + " not found")));
    }

    @GetMapping("/{id}/info")
    public ProductDto getProductDetailById(@PathVariable Long id) {
        return converter.productToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product ID: " + id + " not found")));
    }

    @GetMapping("/{id}/comments/my")
    public List<CommentDto> getUsersComments(@PathVariable Long id, @RequestHeader String username) {
        return productService.findCommentsByUserAndProduct(username, id).stream().map(comment -> converter.commentToDto(comment, username)).collect(Collectors.toList());
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getComments(@PathVariable Long id, @RequestHeader(required = false) String username) {
        return productService.findCommentsByProduct(id).stream().map(comment -> converter.commentToDto(comment, username)).collect(Collectors.toList());
    }

    @PostMapping("/comment")
    public void saveComment(@RequestBody CommentDto commentDto, @RequestHeader String username) {
        productService.saveComment(commentDto, username);
    }

    @GetMapping("/{id}/purchase")
    public StringResponse getInformationAboutPurchase(@PathVariable Long id, @RequestHeader(required = false) String username) {
        return new StringResponse(Boolean.toString(productService.findPurchase(id, username)));
    }

    @GetMapping("/filter")
    public List<ProductDto> getProductByFilter(@RequestParam(name = "min_price", required = false) BigDecimal minPriceLimit, @RequestParam(name = "max_price", required = false) BigDecimal maxPriceLimit) {
        return productService.findAllProductsByPrice(minPriceLimit, maxPriceLimit)
                .stream().map(converter::productToDto)
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
        return converter.productToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product ID: " + productDto.getId() + " not found"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productService.save(product);
        return converter.productToDto(product);
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
