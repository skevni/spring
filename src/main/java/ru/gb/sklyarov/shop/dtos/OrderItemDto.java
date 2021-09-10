package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.OrderItem;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long product_id;
    private String title;
    private double price;
    private double totalPrice;
    private int quantity;
    private Long id;

    public OrderItemDto(OrderItem orderItem) {
        this.title = orderItem.getProduct().getTitle();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
        this.totalPrice = orderItem.getTotalPrice();
        this.product_id = orderItem.getProduct().getId();
        this.id = orderItem.getProduct().getId();
    }

    public OrderItemDto(CartItemDto cartItemDto) {
        this.title = cartItemDto.getTitle();
        this.price = cartItemDto.getPrice();
        this.quantity = cartItemDto.getQuantity();
        this.totalPrice = cartItemDto.getTotalPrice();
        this.product_id = cartItemDto.getId();
    }
}
