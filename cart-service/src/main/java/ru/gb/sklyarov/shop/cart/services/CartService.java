package ru.gb.sklyarov.shop.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.cart.util.Cart;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${utils.cart.prefix}")
    private String cartPrefix;

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public Cart getCart(String cartKey) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(cartKey))) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public Cart getCartByKey(String cartId) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(cartPrefix + cartId))) {
            redisTemplate.opsForValue().set(cartPrefix + cartId, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartPrefix + cartId);
    }

    public void updateCart(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void updateCartByKey(String cartId, Cart cart) {
        redisTemplate.opsForValue().set(cartPrefix + cartId, cart);
    }

    public void addItemToCart(String cartKey, ProductDto productDto) {
        execute(cartKey, cart -> {
            cart.add(productDto);
        });
    }

    public void removeItemFromCart(String cartKey, Long productId) {
        execute(cartKey, cart -> cart.remove(productId));
    }

    public void reduceItemInCart(String cartKey, Long productId) {
        execute(cartKey, cart -> cart.reduce(productId));
    }

    public void clearCart(String cartKey) {
        execute(cartKey, Cart::clear);
    }

    public void mergeCart(String userCartKey, String guestCartKey) {
        Cart guestCart = getCartByKey(guestCartKey);
        Cart userCart = getCartByKey(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }

    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }
}
