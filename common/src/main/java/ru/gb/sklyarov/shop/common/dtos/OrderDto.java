package ru.gb.sklyarov.shop.common.dtos;


import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private String phone;
    private String address;
    private BigDecimal totalPrice;
    private String username;
    private List<OrderItemDto> cartItems;
    private boolean isPaid;
    private String statusPay;


    public OrderDto() {
    }

    public OrderDto(Long id, String phone, String address, BigDecimal totalPrice, String username,List<OrderItemDto> cartItems, boolean isPaid) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
        this.isPaid = isPaid;
        this.statusPay = isPaid ? "Оплачен" : "Не оплачен";
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getStatusPay() {
        return statusPay;
    }
}
