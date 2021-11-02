package ru.gb.sklyarov.shop.common.dtos;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private List<OrderItemDto> cartItems;
    private BigDecimal totalPrice;

    public CartDto() {
    }

    public CartDto(List<OrderItemDto> cartItems, BigDecimal totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<OrderItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
