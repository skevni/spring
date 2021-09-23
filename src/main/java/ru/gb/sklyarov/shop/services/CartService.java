package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.entities.Product;
import ru.gb.sklyarov.shop.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.utils.Cart;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final static String CART_PREFIX = "web_shop_cart_";

    private String getCartKey(Principal principal, String cartId) {
        if (principal != null) {
            return CART_PREFIX + principal.getName();
        }
        return CART_PREFIX + cartId;
    }

    public Cart getCart(Principal principal, String cartId) {
        String cartKey = getCartKey(principal, cartId);

        if (Boolean.FALSE.equals(redisTemplate.hasKey(cartKey))) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public Cart getCartByKey(String cartId) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(CART_PREFIX + cartId))) {
            redisTemplate.opsForValue().set(CART_PREFIX + cartId, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(CART_PREFIX + cartId);
    }

    public void updateCart(Principal principal, String cartId, Cart cart) {
        String cartKey = getCartKey(principal, cartId);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void updateCartByKey(String cartId, Cart cart) {
        redisTemplate.opsForValue().set(CART_PREFIX + cartId, cart);
    }

    public void addItemToCart(Principal principal, String cartId, Long productId) {
        Cart cart = getCart(principal, cartId);
        if (cart.add(productId)) {
            updateCart(principal, cartId, cart);
            return;
        }
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to add a product to the cart because the product with id=" + productId + " does not exists"));
        cart.add(product);
        updateCart(principal, cartId, cart);
    }

    public void removeItemFromCart(Principal principal, String cartId, Long id) {
        Cart cart = getCart(principal, cartId);
        cart.remove(id);
        updateCart(principal, cartId, cart);
    }

    public void reduceItemInCart(Principal principal, String cartId, Long id) {
        Cart cart = getCart(principal, cartId);
        cart.reduce(id);
        updateCart(principal, cartId, cart);
    }

    public void clearCart(Principal principal, String cartId) {
        Cart cart = getCart(principal, cartId);
        cart.clear();
        updateCart(principal, cartId, cart);
    }


    public void mergeCart(Principal principal, String cartId) {
        Cart guestCart = getCartByKey(cartId);
        Cart userCart = getCartByKey(principal.getName());
        userCart.merge(guestCart);
        updateCartByKey(cartId, guestCart);
        updateCartByKey(principal.getName(), userCart);
    }
}
