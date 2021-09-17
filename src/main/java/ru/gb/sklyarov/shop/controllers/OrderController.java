package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.dtos.OrderDto;
import ru.gb.sklyarov.shop.services.CartService;
import ru.gb.sklyarov.shop.services.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        orderService.saveOrderWithOrderItems(orderDto, authentication.getName());
        cartService.clearCart();
    }

    @GetMapping
    public List<OrderDto> getUserOrders(@RequestBody Principal principal) {
        return orderService.findAllOrders().stream().filter(o -> o.getUser().getUsername().equals(principal.getName())).map(OrderDto::new).collect(Collectors.toList());
    }

}
