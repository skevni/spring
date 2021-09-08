package ru.gb.sklyarov.shop.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.Product;

@NoArgsConstructor
@Data
public class CartsItem {

    private Long id;
    private String title;
    private double price;
    private int amount;

    public CartsItem(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.amount = 1;
    }

    public void incrementAmount() {
        this.amount++;
    }
}
