package ru.gb.sklyarov.shop.common.dtos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CommentDto {
    private Long id;
    private String dateTime;
    private String username;
    private String comment;
    private Long product_id;
    // т.к. можно можерировать комментарии, то берем user_id не из Principal, а из коммента, если редактируется
    private Long user_id;

//    public CommentDto(Comment comment) {
//        this.id = comment.getId();
//        this.comment = comment.getComment();
//        // TODO: get time from DB
//        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//
//        this.dateTime = dateFormat.format(comment.getCreatedAt());
//        this.username = comment.getUser().getUsername();
//        this.user_id = comment.getUser().getId();
//        this.product_id = comment.getProduct().getId();
//    }


    public CommentDto() {
    }

    public CommentDto(Long id, String dateTime, String username, String comment, Long product_id, Long user_id) {
        this.id = id;
        this.dateTime = dateTime;
        this.username = username;
        this.comment = comment;
        this.product_id = product_id;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
