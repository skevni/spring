package ru.gb.sklyarov.shop.cart;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
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


    public void add(CartsContent newCartsContent) {
        if (cartsContents.stream().anyMatch(c -> c.getTitle().equals(newCartsContent.getTitle()))) {
            cartsContents.stream().filter(c -> c.getTitle().equals(newCartsContent.getTitle())).findFirst().orElseThrow(() -> new RuntimeException("")).incrementAmount();
        } else {
            cartsContents.add(newCartsContent);
        }
    }

    public void delete(CartsContent cartsContent) {
        cartsContents.remove(cartsContent);
    }
}
