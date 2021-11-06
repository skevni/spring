package ru.gb.sklyarov.shop.order.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.sklyarov.shop.common.dtos.OrderDto;
import ru.gb.sklyarov.shop.common.dtos.OrderItemDto;
import ru.gb.sklyarov.shop.common.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.order.entities.Order;
import ru.gb.sklyarov.shop.order.entities.OrderItem;
import ru.gb.sklyarov.shop.order.integration.AuthServiceIntegration;
import ru.gb.sklyarov.shop.order.integration.CartServiceIntegration;
import ru.gb.sklyarov.shop.order.repositories.OrderRepository;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final AuthServiceIntegration authServiceIntegration;
    private final CartServiceIntegration cartServiceIntegration;

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
    public Order saveOrderWithOrderItems(OrderDto orderDto, String username) {
        Order order = new Order();
        order.setPhone(orderDto.getPhone());
        order.setAddress(orderDto.getAddress());
        order.setOrderDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        order.setUserId(authServiceIntegration.getUserByUsername(username).getUserId());
        order.setTotalPrice(orderDto.getTotalPrice());

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto orderItemdto : orderDto.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(orderItemdto.getPrice());
            orderItem.setTotalPrice(orderItemdto.getTotalPrice());
            orderItem.setQuantity(orderItemdto.getQuantity());
            orderItem.setProductId(orderItemdto.getProduct_id());
            orderItem.setOrder(order);
            orderItems.add(orderItem);

        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);

        cartServiceIntegration.clear(username);

        return order;
    }

    public List<Order> findAllByUsername(String username) {
        return orderRepository.findByUserId(authServiceIntegration.getUserByUsername(username).getUserId());
    }

    public List<Order> findAllByUsernameAndProductId(String username, Long productId) {
        return orderRepository.findByUserIdAndProductId(authServiceIntegration.getUserByUsername(username).getUserId(), productId);
    }

    @Transactional
    public void setOrderPaid(Long orderId, boolean isPaid){
        orderRepository.setOrderPaid(orderId, isPaid);
    }
}
