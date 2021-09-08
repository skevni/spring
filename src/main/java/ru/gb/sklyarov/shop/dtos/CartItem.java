package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.Product;

@NoArgsConstructor
@Data
public class CartItem {

    private Long id;
    private String title;
    private double price;
    private double totalPrice;
    private int quantity;

    public CartItem(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.totalPrice = product.getPrice();
        this.quantity = 1;
    }

    public void changeQuantity(int delta) {
        quantity += delta;
        if (quantity < 0) {
            quantity = 0;
        }
        totalPrice = price * quantity;
    }
}
