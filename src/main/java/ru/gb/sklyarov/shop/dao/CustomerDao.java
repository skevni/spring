package ru.gb.sklyarov.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.SessionFactoryShop;
import ru.gb.sklyarov.shop.model.Customer;

import java.util.List;


@Component
public class CustomerDao {
    private final SessionFactory factory;

    @Autowired
    public CustomerDao(SessionFactoryShop sessionFactory) {
        this.factory = sessionFactory.getSessionFactory();
    }

    public Customer findById(Long id) {
        Customer customer;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            customer = session.find(Customer.class, id);
            session.getTransaction().commit();
        }
        return customer;
    }

    public List<Customer> findAll() {
        List<Customer> customers;
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            customers = session.createQuery(" from Customer ", Customer.class).getResultList();
            session.getTransaction().commit();
        }
        return customers;
    }

    public void save(Customer customer) {
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        }
    }
}
