package ru.gb.sklyarov.shop.common.dtos;

import java.util.List;

public class CartDto {
    private List<OrderItemDto> cartItems;
    private double totalPrice;

    public CartDto() {
    }

    public CartDto(List<OrderItemDto> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<OrderItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
