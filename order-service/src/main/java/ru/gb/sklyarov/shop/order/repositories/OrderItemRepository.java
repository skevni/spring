package ru.gb.sklyarov.shop.order.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.order.entities.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
