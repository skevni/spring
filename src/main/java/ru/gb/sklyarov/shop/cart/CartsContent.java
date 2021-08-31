package ru.gb.sklyarov.shop.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.dtos.ProductDto;

@NoArgsConstructor
@Getter
public class CartsContent {

    private String title;
    private double price;
    private int amount;

    public CartsContent(String title, double price, int amount) {
        this.title = title;
        this.price = price;
        this.amount = amount;
    }

    public void incrementAmount(){
        this.amount++;
    }
}
