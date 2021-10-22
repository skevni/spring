package ru.gb.sklyarov.shop.common.dtos;

public class CartItemDto {
//TODO: разобраться зачем у меня дублирующий класс OrderItemDto
    private Long id;
    private String title;
    private double price;
    private double totalPrice;
    private int quantity;

    public CartItemDto() {
    }

    public CartItemDto(Long id, String title, double price, double totalPrice, int quantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public void changeQuantity(int delta) {
        quantity += delta;
        if (quantity < 0) {
            quantity = 0;
        }
        totalPrice = price * quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
