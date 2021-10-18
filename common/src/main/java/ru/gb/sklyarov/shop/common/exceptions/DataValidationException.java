package ru.gb.sklyarov.shop.common.exceptions;

import java.util.List;

public class DataValidationException extends RuntimeException {
    private final List<String> messages;

    public DataValidationException(List<String> messages) {
        this.messages = messages;

    }

    public List<String> getMessages() {
        return messages;
    }
}
