package ru.gb.sklyarov.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.model.Product;
import ru.gb.sklyarov.shop.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProductsList(Model model){
        List<Product> productList = productService.showAll();
        model.addAttribute("products", productList);
        return "products";
    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable Long id){
        model.addAttribute("product", productService.findByIs(id));
        return "product_info";
    }

    @GetMapping("/create")
    public String createProduct(){
        return "product_create";
    }

    @PostMapping("/create")
    public String addProduct(@RequestParam String title, @RequestParam double cost){
        productService.addProduct(new Product(title,cost));
        return "redirect:/products";
    }
}
