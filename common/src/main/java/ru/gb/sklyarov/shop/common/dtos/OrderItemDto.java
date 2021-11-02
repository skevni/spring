package ru.gb.sklyarov.shop.common.dtos;

import java.math.BigDecimal;

public class OrderItemDto {
    private Long product_id;
    private String title;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private int quantity;
//    private Long id;

    public OrderItemDto() {
    }

//    public OrderItemDto(Long product_id, String title, double price, double totalPrice, int quantity, Long id) {
    public OrderItemDto(Long product_id, String title, BigDecimal price, BigDecimal totalPrice, int quantity) {
        this.product_id = product_id;
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
        totalPrice = price.multiply(BigDecimal.valueOf(quantity));
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
