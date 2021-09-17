package ru.gb.sklyarov.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.entities.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
