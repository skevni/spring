package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.dtos.AuthRequest;
import ru.gb.sklyarov.shop.dtos.OrderDto;
import ru.gb.sklyarov.shop.entities.Order;
import ru.gb.sklyarov.shop.services.CartService;
import ru.gb.sklyarov.shop.services.OrderService;
import ru.gb.sklyarov.shop.services.UserService;

import java.security.Principal;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDto orderDto, @RequestBody Principal principal) {
        Order order = new Order();
        order.setOrderDate(orderDto.getOrderDate());
        order.setPaid(orderDto.isPayd());
        order.setPhone(orderDto.getPhone());
        order.setAddress(order.getAddress());
        order.setOrderItems(orderDto.getOrderItems());
        order.setUser(userService.findByUsername(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("User " + principal.getName() +" not found in the database.")));
        orderService.saveOrder(order);
        cartService.clearCart();
    }

    @GetMapping
    public List<OrderDto> getUserOrders(@RequestBody Principal principal){
        return orderService.findAllOrders().stream().filter(o -> o.getUser().getUsername().equals(principal.getName())).map(OrderDto::new).collect(Collectors.toList());
    }

}
