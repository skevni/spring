package ru.gb.sklyarov.shop.core.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.core.ms.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceIsLessThanEqual(BigDecimal maxPriceLimit);

    List<Product> findAllByPriceIsGreaterThanEqual(BigDecimal minPriceLimit);

    List<Product> findAllByPriceBetween(BigDecimal minPriceLimit, BigDecimal maxPriceLimit);

    // TODO: переделать на rest
//    @Query(value = "select count(distinct ord.id) from orders ord inner join order_items oi on oi.order_id = ord.id" +
//            " where ord.user_id = :userId and oi.product_id=:productId", nativeQuery = true)
//    int productInPurchase(long userId, long productId);

}