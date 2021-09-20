package ru.gb.sklyarov.shop.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.entities.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(value = "orders.for-front")
    @Query("SELECT ord FROM Order ord WHERE ord.user.username = :username")
    List<Order> findByUsername(String username);
}
