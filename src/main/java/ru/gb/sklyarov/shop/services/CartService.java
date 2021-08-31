package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.cart.Cart;
import ru.gb.sklyarov.shop.cart.CartsContent;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;

    public void add(CartsContent cartsContent) {
        cart.add(cartsContent);
    }

    public void delete(String title) {
        cart.delete(title);
    }

    public List<CartsContent> getCartContent() {
        return cart.getCartsContents();
    }
}
