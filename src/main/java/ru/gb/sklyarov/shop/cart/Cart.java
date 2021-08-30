package ru.gb.sklyarov.shop.cart;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Cart {
    private List<CartsContent> cartsContents;

    public Cart() {
        cartsContents = new ArrayList<>();
    }

    public List<CartsContent> getCartsContents() {
        return cartsContents;
    }


    public boolean add(CartsContent newCartsContent) {

        return cartsContents.add(newCartsContent);

    }

    public void delete(int cartsContentIndex) {
        if (cartsContentIndex >= 0 && cartsContentIndex < cartsContents.size()) {
            cartsContents.remove(cartsContentIndex);
        }
        throw new IndexOutOfBoundsException("The requested index (index=" + cartsContentIndex + ") is out of bounds of the array");
    }
}
