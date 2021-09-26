package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.cart.CartsContent;
import ru.gb.sklyarov.shop.services.CartService;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<CartsContent> getCartsContent() {
        return cartService.getCartContent();
    }

    @DeleteMapping("/{id}")
    public void deleteCartsContent(@PathVariable(name = "id") Long id) {
        cartService.delete(id);
    }
}
