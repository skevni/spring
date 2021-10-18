package ru.gb.sklyarov.shop.common.exceptions;

import java.util.List;

public class ShopAuthException extends RuntimeException {
    private final List<String> messages;

    public ShopAuthException(List<String> messages) {
        this.messages = messages;

    }

    public List<String> getMessages() {
        return messages;
    }
}
