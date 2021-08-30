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

    public boolean add(CartsContent cartsContent) {
        return cart.add(cartsContent);
    }

    public void delete(int cartsContentIndex) {
        cart.delete(cartsContentIndex);
    }

    public List<CartsContent> getCartContent() {
        return cart.getCartsContents();
    }
}
