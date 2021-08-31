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

    public int getCartContentIdByTitle(String title){
        for (int i = 0; i < cartsContents.size(); i++) {
            if (cartsContents.get(i).getTitle().equals(title)){
                return i;
            }
        }
        throw  new ArrayIndexOutOfBoundsException("Nothing to delete");
    }
    public void delete(String tittle) {
        cartsContents.remove(getCartContentIdByTitle(tittle));
    }
}
