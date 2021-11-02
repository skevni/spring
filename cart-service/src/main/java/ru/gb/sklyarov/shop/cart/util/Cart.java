package ru.gb.sklyarov.shop.cart.util;

import lombok.Getter;
import lombok.Setter;
import ru.gb.sklyarov.shop.common.dtos.OrderItemDto;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public class Cart {
    private List<OrderItemDto> cartItems;
    private BigDecimal totalPrice;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public List<OrderItemDto> getCartItems() {
        return cartItems;
    }


    public void add(ProductDto product) {
        for (OrderItemDto oi: cartItems){
            if (oi.getProduct_id().equals(product.getId())){
                oi.changeQuantity(1);
                recalculate();
                return;
            }
        }
        cartItems.add(new OrderItemDto(product.getId(), product.getTitle(), product.getPrice(), product.getPrice(), 1));
        recalculate();
    }

    public void reduce(Long productId) {
        for (Iterator<OrderItemDto> iterator = cartItems.iterator(); iterator.hasNext(); ) {
            OrderItemDto item = iterator.next();
            if (item.getProduct_id().equals(productId)) {
                item.changeQuantity(-1);

                if (item.getQuantity() <= 0) {
                    iterator.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        cartItems.removeIf(item -> item.getProduct_id().equals(productId));
        recalculate();
    }

    public void clear() {
        cartItems.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (OrderItemDto item : cartItems) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
    }

    public void merge(Cart another) {
        for (OrderItemDto anotherItem : another.cartItems) {
            boolean merged = false;
            for (OrderItemDto item : cartItems) {
                if (item.getProduct_id().equals(anotherItem.getProduct_id())) {
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
