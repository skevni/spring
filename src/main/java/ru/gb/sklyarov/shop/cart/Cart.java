package ru.gb.sklyarov.shop.cart;

import java.util.Collections;
import java.util.List;

public class Cart {
    private List<CartsContent> cartsContents;

    public Cart() {
        cartsContents = Collections.emptyList();
    }

    public List<CartsContent> getCartsContents() {
        return cartsContents;
    }

    public boolean add(CartsContent cartsContent) {
        return cartsContents.add(cartsContent);
    }

    public void delete(int cartsContentIndex) {
        if (cartsContentIndex >= 0 && cartsContentIndex < cartsContents.size()) {
            cartsContents.remove(cartsContentIndex);
        }
        throw new IndexOutOfBoundsException("The requested index (index=" + cartsContentIndex + ") is out of bounds of the array");
    }
}
