package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.entities.OrderItem;
import ru.gb.sklyarov.shop.repositories.OrderItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void saveAll(List<OrderItem> orderItems){
        orderItemRepository.saveAll(orderItems);
    }
}
