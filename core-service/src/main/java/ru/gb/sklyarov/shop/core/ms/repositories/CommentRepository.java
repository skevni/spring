package ru.gb.sklyarov.shop.core.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.core.ms.entities.Comment;
import ru.gb.sklyarov.shop.core.ms.entities.Product;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUserIdAndProduct(Long userId, Product product);

    List<Comment> findAllByProduct(Product product);
}
