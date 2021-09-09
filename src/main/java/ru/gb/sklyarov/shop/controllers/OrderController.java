package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import ru.gb.sklyarov.shop.dtos.CartItemDto;
import ru.gb.sklyarov.shop.dtos.OrderDto;

import ru.gb.sklyarov.shop.dtos.OrderItemDto;
import ru.gb.sklyarov.shop.entities.Cart;
import ru.gb.sklyarov.shop.entities.Order;
import ru.gb.sklyarov.shop.entities.OrderItem;
import ru.gb.sklyarov.shop.services.CartService;
import ru.gb.sklyarov.shop.services.OrderService;
import ru.gb.sklyarov.shop.services.ProductService;
import ru.gb.sklyarov.shop.services.UserService;
import ru.gb.sklyarov.shop.utils.CartUtil;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public void createOrder(@RequestBody List<CartItemDto> cartItemDtos, @RequestBody Principal principal) {
        Order order = new Order();
        order.setUser(userService.findByUsername(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("User " + principal.getName() +" not found in the database.")));
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItemDto cartItemDto : cartItemDtos) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(cartItemDto.getPrice());
            orderItem.setTotalPrice(cartItemDto.getTotalPrice());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setProduct(cartService.getProductById(cartItemDto.getId()));
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        orderService.saveOrder(order);
        cartService.clearCart();
    }

    @GetMapping
    public List<OrderDto> getUserOrders(@RequestBody Principal principal){
        return orderService.findAllOrders().stream().filter(o -> o.getUser().getUsername().equals(principal.getName())).map(OrderDto::new).collect(Collectors.toList());
    }

}
