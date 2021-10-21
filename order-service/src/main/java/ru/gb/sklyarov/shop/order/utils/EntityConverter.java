package ru.gb.sklyarov.shop.order.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.order.entities.Order;
import ru.gb.sklyarov.shop.order.entities.OrderItem;
import ru.gb.sklyarov.shop.order.integration.CoreServiceIntegration;
import ru.gb.sklyarov.shop.common.dtos.OrderDto;
import ru.gb.sklyarov.shop.common.dtos.OrderItemDto;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;

import java.util.stream.Collectors;

@Component
public class EntityConverter {

    private CoreServiceIntegration coreServiceIntegration;

    @Autowired
    public void setCoreServiceIntegration(CoreServiceIntegration coreServiceIntegration){
        this.coreServiceIntegration = coreServiceIntegration;
    }

    public OrderDto orderToDto(Order order) {
        return new OrderDto(order.getId(), order.getPhone(), order.getAddress(), order.getTotalPrice(), order.getOrderItems().stream().map(this::orderItemToDto).collect(Collectors.toList()));
    }

    // В моем случае Title не хранится в ордерах.
    // Вариант мне не нравится. Необходимо продукт вытаскивать по rest, хотя сущности лежат в одной БД.
    // Хочу, чтобы показали какой-нибудь рабочий вариант.
    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        ProductDto productDto = coreServiceIntegration.getProductById(orderItem.getProductId());
        return new OrderItemDto(orderItem.getProductId(), productDto.getTitle(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getQuantity());
    }


}
