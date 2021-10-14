package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.Comment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String dateTime;
    private String username;
    private String comment;
    private Long product_id;
    // т.к. можно можерировать комментарии, то берем user_id не из Principal, а из коммента, если редактируется
    private Long user_id;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        // TODO: get time from DB
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        this.dateTime = dateFormat.format(comment.getCreatedAt());
        this.username = comment.getUser().getUsername();
        this.user_id = comment.getUser().getId();
        this.product_id = comment.getProduct().getId();
    }
}
