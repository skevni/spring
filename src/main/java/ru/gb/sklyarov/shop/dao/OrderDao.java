package ru.gb.sklyarov.shop.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.SessionFactoryShop;
import ru.gb.sklyarov.shop.model.Customer;
import ru.gb.sklyarov.shop.model.Product;

import java.util.List;

@Component
public class OrderDao {
    private SessionFactory factory;

    @Autowired
    public OrderDao(SessionFactoryShop sessionFactory){
        this.factory = sessionFactory.getSessionFactory();
    }

    public List<Product> getProductsByCustomer(Customer customer){

        return null;
    }

    public List<Customer> getCustomersByProduct(Product product){

        return null;
    }
}
