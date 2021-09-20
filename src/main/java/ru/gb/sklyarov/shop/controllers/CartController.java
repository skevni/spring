package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.sklyarov.shop.services.CartService;
import ru.gb.sklyarov.shop.utils.Cart;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{cartId}")
    public Cart getCartsContent(@PathVariable(name = "cartId") String cartId) {
        return cartService.getCart(cartId);
    }

    @GetMapping("/{cartId}/remove/{id}")
    public void deleteCartsContent(@PathVariable(name = "id") Long id, @PathVariable(name = "cartId") String cartId) {
        cartService.removeItemFromCart(cartId, id);
    }

    @GetMapping("/{cartId}/add/{id}")
    public void addToCart(@PathVariable Long id, @PathVariable(name = "cartId") String cartId) {
        cartService.addItemToCart(cartId, id);
    }

    @GetMapping("/{cartId}/reduce/{id}")
    public void reduceItemInCart(@PathVariable Long id, @PathVariable(name = "cartId") String cartId) {
        cartService.reduceItemInCart(cartId, id);
    }
}
