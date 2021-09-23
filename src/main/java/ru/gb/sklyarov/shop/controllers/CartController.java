package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.sklyarov.shop.dtos.StringResponse;
import ru.gb.sklyarov.shop.services.CartService;
import ru.gb.sklyarov.shop.utils.Cart;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/generate")
    public StringResponse generateCartUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{cartId}")
    public Cart getCartsContent(Principal principal, @PathVariable(name = "cartId") String cartId) {
        return cartService.getCart(principal, cartId);
    }

    @GetMapping("/{cartId}/remove/{productId}")
    public void deleteCartsContent(Principal principal, @PathVariable Long productId, @PathVariable(name = "cartId") String cartId) {
        cartService.removeItemFromCart(principal, cartId, productId);
    }

    @GetMapping("/{cartId}/add/{productId}")
    public void addToCart(Principal principal, @PathVariable Long productId, @PathVariable(name = "cartId") String cartId) {
        cartService.addItemToCart(principal, cartId, productId);
    }

    @GetMapping("/{cartId}/reduce/{productId}")
    public void reduceItemInCart(Principal principal, @PathVariable Long productId, @PathVariable(name = "cartId") String cartId) {
        cartService.reduceItemInCart(principal, cartId, productId);
    }

    @GetMapping("/{cartId}/merge")
    public void mergeCart(Principal principal, @PathVariable String cartId) {
        cartService.mergeCart(principal, cartId);
    }
}
