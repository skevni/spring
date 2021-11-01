package ru.gb.sklyarov.shop.core.ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.core.ms.entities.Comment;
import ru.gb.sklyarov.shop.core.ms.entities.Product;
import ru.gb.sklyarov.shop.core.ms.repositories.CommentRepository;

import java.util.List;

@Service

public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findCommentsByUserIdAndProduct(Long userId, Product product) {
        return commentRepository.findAllByUserIdAndProduct(userId, product);
    }

    public List<Comment> findCommentsByProduct(Product product) {
        return commentRepository.findAllByProduct(product);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
