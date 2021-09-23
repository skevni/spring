package ru.gb.sklyarov.shop.utils;

import lombok.Getter;
import lombok.Setter;
import ru.gb.sklyarov.shop.dtos.CartItemDto;
import ru.gb.sklyarov.shop.entities.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public class Cart {
    private List<CartItemDto> cartItems;
    private double totalPrice;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }


    public void add(Product product) {
        cartItems.add(new CartItemDto(product));
        recalculate();
    }

    public boolean add(Long id) {
        for (CartItemDto item : cartItems) {
            if (item.getId().equals(id)) {
                item.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void reduce(Long id) {
        for (Iterator<CartItemDto> iterator = cartItems.iterator(); iterator.hasNext(); ) {
            CartItemDto item = iterator.next();
            if (item.getId().equals(id)) {
                item.changeQuantity(-1);

                if (item.getQuantity() <= 0) {
                    iterator.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long id) {
        cartItems.removeIf(item -> item.getId().equals(id));
        recalculate();
    }

    public void clear() {
        cartItems.clear();
        totalPrice = 0;
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItemDto item : cartItems) {
            totalPrice += item.getTotalPrice();
        }
    }

    public void merge(Cart another) {
        for (CartItemDto anotherItem : another.cartItems) {
            boolean merged = false;
            for (CartItemDto item : cartItems) {
                if (item.getId().equals(anotherItem.getId())) {
                    item.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                cartItems.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}
