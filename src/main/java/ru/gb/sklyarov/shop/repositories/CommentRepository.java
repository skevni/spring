package ru.gb.sklyarov.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.entities.Comment;
import ru.gb.sklyarov.shop.entities.Product;
import ru.gb.sklyarov.shop.entities.User;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByUserAndProduct(User user, Product product);
}
