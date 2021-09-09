package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.entities.Product;
import ru.gb.sklyarov.shop.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.utils.CartUtil;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private CartUtil cart;

    @PostConstruct
    public void init() {
        this.cart = new CartUtil();
    }

    public void addItemToCart(Long id) {
        if (cart.add(id)) {
            return;
        }
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to add a product to the cart because the product with id=" + id + " does not exists"));
        cart.add(product);
    }

    public void removeItemFromCart(Long id) {
        cart.remove(id);
    }

    public void reduceItemInCart(Long id) {
        cart.reduce(id);
    }

    public CartUtil getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }

    public Product getProductById(Long id){
        return productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cann't find product by ID"));
    }
}
