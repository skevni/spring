package ru.gb.sklyarov.shop.cart;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private final List<CartsItem> cartsContents;

    public Cart() {
        cartsContents = new ArrayList<>();
    }

    public List<CartsItem> getCartsContents() {
        return cartsContents;
    }


    public void add(CartsItem newCartsContent) {
        if (cartsContents.stream().anyMatch(c -> c.getTitle().equals(newCartsContent.getTitle()))) {
            cartsContents.stream().filter(c -> c.getTitle().equals(newCartsContent.getTitle())).findFirst().orElseThrow(() -> new RuntimeException("")).incrementAmount();
        } else {
            cartsContents.add(newCartsContent);
        }
    }

    public int getCartContentIdByTitle(String title) {
        for (int i = 0; i < cartsContents.size(); i++) {
            if (cartsContents.get(i).getTitle().equals(title)) {
                return i;
            }
        }
        throw new ArrayIndexOutOfBoundsException("Nothing to delete");
    }

    public void delete(Long id) {
//        cartsContents.remove(cartsContent);
        for (int i = 0; i < cartsContents.size(); i++) {
            if (cartsContents.get(i).getId().equals(id)){
                cartsContents.remove(i);
                return;
            }
        }
    }
}
