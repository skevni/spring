package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.entities.Product;
import ru.gb.sklyarov.shop.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.utils.Cart;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final static String CART_PREFIX = "cart_shop_";

    public Cart getCart(String cartId) {
        if (!isCartExists(cartId)){
            cartId = generateUniqueCartNumber();
        }
        if (Boolean.FALSE.equals(redisTemplate.hasKey(cartId))) {
            redisTemplate.opsForValue().set(cartId, new Cart());
        }
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartId);
        return cart;
    }

    public void updateCart(String cartId, Cart cart) {
        redisTemplate.opsForValue().set(cartId, cart);
    }

    public void addItemToCart(String cartId, Long id) {
        Cart cart = getCart(cartId);
        if (cart.add(id)) {
            updateCart(cartId, cart);
            return;
        }
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to add a product to the cart because the product with id=" + id + " does not exists"));
        cart.add(product);
        updateCart(cartId, cart);
    }

    public void removeItemFromCart(String cartId, Long id) {
        Cart cart = getCart(cartId);
        cart.remove(id);
        updateCart(cartId, cart);
    }

    public void reduceItemInCart(String cartId, Long id) {
        Cart cart = getCart(cartId);
        cart.reduce(id);
        updateCart(cartId, cart);
    }

    public void clearCart(String cartId) {
        Cart cart = getCart(cartId);
        cart.clear();
        updateCart(cartId, cart);
    }

    public boolean isCartExists(String cartId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(cartId));
    }

    private String generateUniqueCartNumber(){
        return CART_PREFIX + UUID.randomUUID();
    }
}
