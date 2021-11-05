package ru.gb.sklyarov.shop.order.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.common.dtos.OrderDto;
import ru.gb.sklyarov.shop.common.dtos.OrderItemDto;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;
import ru.gb.sklyarov.shop.order.entities.Order;
import ru.gb.sklyarov.shop.order.entities.OrderItem;
import ru.gb.sklyarov.shop.order.integration.AuthServiceIntegration;
import ru.gb.sklyarov.shop.order.integration.CoreServiceIntegration;

import java.util.stream.Collectors;

@Component
public class EntityConverter {

    private CoreServiceIntegration coreServiceIntegration;
    @Autowired
    private AuthServiceIntegration authServiceWebClient;

    @Autowired
    public void setCoreServiceIntegration(CoreServiceIntegration coreServiceIntegration){
        this.coreServiceIntegration = coreServiceIntegration;
    }

    public OrderDto orderToDto(Order order) {
//        UserDto userDto = authServiceWebClient.getUserByUserId(order.getUserId());
        String username = "";
//        if (userDto != null){
//            username = userDto.getUsername();
//        }
        return new OrderDto(order.getId(), order.getPhone(), order.getAddress(), order.getTotalPrice(), username,order.getOrderItems().stream().map(this::orderItemToDto).collect(Collectors.toList()));
    }

    // В моем случае Title не хранится в ордерах.
    // Вариант мне не нравится. Необходимо продукт вытаскивать по rest, хотя сущности лежат в одной БД.
    // Хочу, чтобы показали какой-нибудь рабочий вариант.
    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        ProductDto productDto = coreServiceIntegration.getProductById(orderItem.getProductId());
        return new OrderItemDto(orderItem.getProductId(), productDto.getTitle(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getQuantity());
    }


}
