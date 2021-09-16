package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.sklyarov.shop.dtos.OrderDto;
import ru.gb.sklyarov.shop.dtos.OrderItemDto;
import ru.gb.sklyarov.shop.entities.Order;
import ru.gb.sklyarov.shop.entities.OrderItem;
import ru.gb.sklyarov.shop.entities.Product;
import ru.gb.sklyarov.shop.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.repositories.OrderItemRepository;
import ru.gb.sklyarov.shop.repositories.OrderRepository;

import java.security.Principal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ProductService productService;
    private final UserService userService;

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public void saveOrderWithOrderItems(OrderDto orderDto, String currentUser) {
        Order order = new Order();
        order.setPhone(orderDto.getPhone());
        order.setAddress(orderDto.getAddress());
        order.setOrderDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        order.setUser(userService.findByUsername(currentUser).orElseThrow(() -> new UsernameNotFoundException("User " + currentUser + " not found in the database.")));

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto orderItemdto : orderDto.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(orderItemdto.getPrice());
            orderItem.setTotalPrice(orderItemdto.getTotalPrice());
            orderItem.setQuantity(orderItemdto.getQuantity());
            orderItem.setProduct(getProductById(orderItemdto.getId()));
            orderItem.setOrder(order);
            orderItems.add(orderItem);

        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);
//        orderItemService.saveAll(orderItems);
    }

    public Product getProductById(Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cann't find product by ID"));
    }
}
