package ru.gb.sklyarov.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.dao.OrderDao;
import ru.gb.sklyarov.shop.model.Customer;
import ru.gb.sklyarov.shop.model.Product;

import java.util.List;

@Service
public class OrderService {
    private OrderDao orderRepository;

    @Autowired
    public OrderService(OrderDao orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Product> getProductsByCustomer(Customer customer) {
        return orderRepository.getProductsByCustomer(customer);
    }

    public List<Customer> getCustomersByProduct(Product product) {
        return orderRepository.getCustomersByProduct(product);
    }

}
