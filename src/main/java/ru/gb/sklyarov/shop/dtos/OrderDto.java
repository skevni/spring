package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String phone;
    private String address;
    private double totalPrice;
    private List<OrderItemDto> cartItems;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.cartItems = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.totalPrice = order.getTotalPrice();
    }

}
