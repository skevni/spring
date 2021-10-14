package ru.gb.sklyarov.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.entities.Comment;
import ru.gb.sklyarov.shop.entities.Product;
import ru.gb.sklyarov.shop.entities.User;
import ru.gb.sklyarov.shop.repositories.CommentRepository;

import java.util.List;

@Service

public class CommentService {
    private CommentRepository commentRepository;
    @Autowired
    public void setCommentRepository(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public List<Comment> findCommentsByUserAndProduct(User user, Product product) {
        return commentRepository.findAllByUserAndProduct(user, product);
    }

    public List<Comment> findCommentsByProduct(Product product) {
        return commentRepository.findAllByProduct(product);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
