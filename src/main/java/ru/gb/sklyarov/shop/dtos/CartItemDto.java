package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.Product;

@NoArgsConstructor
@Data
public class CartItemDto {
//TODO: разобраться зачем у меня дублирующий класс OrderItemDto
    private Long id;
    private String title;
    private double price;
    private double totalPrice;
    private int quantity;

    public CartItemDto(Product product) {
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
