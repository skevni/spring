package ru.gb.sklyarov.shop.common.dtos;


import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private String phone;
    private String address;
    private BigDecimal totalPrice;
    private List<OrderItemDto> cartItems;


    public OrderDto() {
    }

    public OrderDto(Long id, String phone, String address, BigDecimal totalPrice, List<OrderItemDto> cartItems) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<OrderItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
