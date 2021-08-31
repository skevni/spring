package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.cart.CartsContent;
import ru.gb.sklyarov.shop.dtos.ProductDto;
import ru.gb.sklyarov.shop.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.models.Product;
import ru.gb.sklyarov.shop.services.CartService;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<CartsContent> getCartsContent(){
        return cartService.getCartContent();
    }

    @DeleteMapping("/{title}")
    public void deleteProduct(@PathVariable String title){
        cartService.delete(title);
    }
}
