package ru.gb.sklyarov.shop.exceptions;

import java.sql.Timestamp;

public class ShopError {
    private int status;
    private String message;
    private Timestamp timestamp;

    public ShopError() {
    }

    public ShopError(String message) {
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
