package ru.gb.sklyarov.shop.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CartsContent {

    private Long id;
    private String title;
    private double price;
    private int amount;

    public CartsContent(Long id, String title, double price, int amount) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.amount = amount;
    }

    public void incrementAmount() {
        this.amount++;
    }
}
