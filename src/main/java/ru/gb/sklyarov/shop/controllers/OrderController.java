package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.dtos.OrderDto;
import ru.gb.sklyarov.shop.services.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public void createOrder(Principal principal, @RequestBody OrderDto orderDto) {
        // Principal vs Authentication
        //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        orderService.saveOrderWithOrderItems(orderDto, principal);

    }

    @GetMapping
    public List<OrderDto> getUserOrders(Principal principal) {
        return orderService.findAllByUsername(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

}
