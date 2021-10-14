package ru.gb.sklyarov.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceIsLessThanEqual(Double maxPriceLimit);

    List<Product> findAllByPriceIsGreaterThanEqual(Double minPriceLimit);

    List<Product> findAllByPriceBetween(Double minPriceLimit, Double maxPriceLimit);

    @Query(value = "select count(distinct ord.id) from orders ord inner join order_items oi on oi.order_id = ord.id" +
            " where ord.user_id = :userId and oi.product_id=:productId", nativeQuery = true)
    int productInPurchase(long userId, long productId);

}