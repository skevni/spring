package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.sklyarov.shop.entities.Order;
import ru.gb.sklyarov.shop.entities.OrderItem;
import ru.gb.sklyarov.shop.repositories.OrderItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;

    @Transactional
    public void saveOrderItems(List<OrderItem> orderItems){
        orderService.saveOrder(new Order()); // пока заглушка

        orderItemRepository.saveAll(orderItems);
    }
}
