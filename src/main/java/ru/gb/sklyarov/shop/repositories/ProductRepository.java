package ru.gb.sklyarov.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceIsLessThanEqual(Double maxPriceLimit);

    List<Product> findAllByPriceIsGreaterThanEqual(Double minPriceLimit);

    List<Product> findAllByPriceBetween(Double minPriceLimit, Double maxPriceLimit);

}
