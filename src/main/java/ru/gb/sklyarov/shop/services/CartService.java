package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.cart.Cart;
import ru.gb.sklyarov.shop.cart.CartsItem;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;

    public void add(CartsItem cartsItem) {
        cart.add(cartsItem);
    }

    public void delete(Long id) {
        cart.delete(id);
    }

    public List<CartsItem> getCartContent() {
        return cart.getCartsContents();
    }
}
