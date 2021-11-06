package ru.gb.sklyarov.shop.order.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.order.entities.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(value = "orders.for-front")
    @Query("SELECT ord FROM Order ord WHERE ord.userId = :userId")
    List<Order> findByUserId(Long userId);

    @EntityGraph(value = "orders.for-front")
    @Query("SELECT ord FROM Order ord join fetch ord.orderItems oi WHERE ord.userId = :userId and oi.productId = :productId")
    List<Order> findByUserIdAndProductId(Long userId, Long productId);

    @EntityGraph(value = "orders.for-front")
    @Query("SELECT ord FROM Order ord WHERE ord.id = :orderId")
    Optional<Order> findById(@NonNull Long orderId);

    @Modifying
    @Query("UPDATE Order SET isPaid = :isPaid WHERE id = :orderId")
    void setOrderPaid(Long orderId, boolean isPaid);
}
