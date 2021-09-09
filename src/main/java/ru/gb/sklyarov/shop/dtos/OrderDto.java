package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.Order;
import ru.gb.sklyarov.shop.entities.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDateTime orderDate;
    private boolean isPayd;
    private String phone;
    private String address;
    private List<OrderItem> orderItems;

    public OrderDto(Order order){
        this.id = order.getId();
        this.isPayd = order.isPaid();
        this.orderDate = order.getOrderDate();
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.orderItems = order.getOrderItems();
    }
}
