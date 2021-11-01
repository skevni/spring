package ru.gb.sklyarov.shop.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.cart.integration.ProductServiceIntegration;
import ru.gb.sklyarov.shop.cart.services.CartService;
import ru.gb.sklyarov.shop.cart.util.Cart;
import ru.gb.sklyarov.shop.common.dtos.CartDto;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;
import ru.gb.sklyarov.shop.common.dtos.StringResponse;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductServiceIntegration productServiceIntegration;

    @GetMapping("/generate")
    public StringResponse generateCartUuid() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{cartId}")
    public CartDto getCartsContent(@RequestHeader(required = false) String username, @PathVariable(name = "cartId") String cartId) {
        Cart cart = cartService.getCart(getCurrentCartUuid(username, cartId));
        return new CartDto(cart.getCartItems(), cart.getTotalPrice());
    }

    @GetMapping("/{cartId}/remove/{productId}")
    public void deleteCartsContent(@RequestHeader(required = false) String username, @PathVariable Long productId, @PathVariable(name = "cartId") String cartId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, cartId), productId);
    }

    @GetMapping("/{cartId}/add/{productId}")
    public void addToCart(@RequestHeader(required = false) String username, @PathVariable Long productId, @PathVariable(name = "cartId") String cartId) {
        ProductDto productDto = productServiceIntegration.getProductById(productId);
        cartService.addItemToCart(getCurrentCartUuid(username, cartId), productDto);
    }

    @GetMapping("/{cartId}/reduce/{productId}")
    public void reduceItemInCart(@RequestHeader(required = false) String username, @PathVariable Long productId, @PathVariable(name = "cartId") String cartId) {
        cartService.reduceItemInCart(getCurrentCartUuid(username, cartId), productId);
    }

    @GetMapping("/{cartId}/merge")
    public void mergeCart(@RequestHeader String username, @PathVariable String cartId) {
        cartService.mergeCart(getCurrentCartUuid(username, null), getCurrentCartUuid(null,cartId));
    }

    @GetMapping("/clear")
    public void clear(@RequestHeader String username ) {
        cartService.clearCart(getCurrentCartUuid(username, null));
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
