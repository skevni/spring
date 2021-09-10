package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.Order;
import ru.gb.sklyarov.shop.entities.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private String phone;
    private String address;
    private List<OrderItemDto> orderItemDtos;

    public OrderDto(Order order){
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.orderItemDtos = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
