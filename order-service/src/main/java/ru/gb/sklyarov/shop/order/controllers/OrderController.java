package ru.gb.sklyarov.shop.order.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.common.dtos.OrderDto;
import ru.gb.sklyarov.shop.order.services.OrderService;
import ru.gb.sklyarov.shop.order.utils.EntityConverter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final EntityConverter converter;

    @PostMapping
    public void createOrder(@RequestHeader String username, @RequestBody OrderDto orderDto) {
        // Principal vs Authentication
        //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        orderService.saveOrderWithOrderItems(orderDto, username);

    }

    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username) {
        return orderService.findAllByUsername(username).stream().map(converter::orderToDto).collect(Collectors.toList());
    }

}
