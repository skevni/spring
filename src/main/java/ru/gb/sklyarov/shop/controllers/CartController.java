package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.services.CartService;
import ru.gb.sklyarov.shop.utils.CartUtil;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public CartUtil getCartsContent() {
        return cartService.getCart();
    }

    @GetMapping("/remove/{id}")
    public void deleteCartsContent(@PathVariable(name = "id") Long id) {
        cartService.removeItemFromCart(id);
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.addItemToCart(id);
    }

    @GetMapping("/reduce/{id}")
    public void reduceItemInCart(@PathVariable Long id) {
        cartService.reduceItemInCart(id);
    }
}
