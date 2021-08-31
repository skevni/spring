package ru.gb.sklyarov.shop.exceptions;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class ShopError {
    private int status;
    private List<String> messages;
    private Timestamp timestamp;

    public ShopError() {
    }

    public ShopError(int statusCode, List<String> messages) {
        this.messages = messages;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = statusCode;
    }

    public ShopError(int statusCode, String message) {
        this(statusCode, List.of(message));
    }

    public ShopError(int statusCode, String... messages) {
        this(statusCode, Arrays.asList(messages));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
