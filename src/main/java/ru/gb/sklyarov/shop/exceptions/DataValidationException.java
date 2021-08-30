package ru.gb.sklyarov.shop.exceptions;

import java.util.List;

public class DataValidationException extends RuntimeException{
    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public DataValidationException(int statusCode, List<String> messages){
        this.messages = messages;

    }
}
