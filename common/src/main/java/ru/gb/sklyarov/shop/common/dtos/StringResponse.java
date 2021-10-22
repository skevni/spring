package ru.gb.sklyarov.shop.common.dtos;

public class StringResponse {
    private String value;

    public StringResponse() {
    }

    public StringResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
