package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.Order;
import ru.gb.sklyarov.shop.entities.OrderItem;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private String phone;
    private String address;
    private List<OrderItemDto> cartItems;

    public OrderDto(Order order) {
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.cartItems = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }


}
