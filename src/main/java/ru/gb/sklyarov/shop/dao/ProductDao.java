package ru.gb.sklyarov.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.SessionFactoryProj;
import ru.gb.sklyarov.shop.model.Product;

import java.util.List;

@Component
public class ProductDao {
    private final SessionFactory factory;

    @Autowired
    public ProductDao(SessionFactoryProj sessionFactory) {
        this.factory = sessionFactory.getSessionFactory();
    }

    public Product findById(Long id) {
        Product product;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            product = session.find(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public List<Product> findAll() {
        List<Product> products;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            products = session.createQuery("from Product", Product.class).getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    public void save(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public Long getMaxId() {
        Long id;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            id = (Long) session.createSQLQuery("select COALESCE((select max(id) from products) ,0) as maxvalue").addScalar("maxvalue", LongType.INSTANCE).uniqueResult();
            session.getTransaction().commit();
        }
        return id;
    }

    public void setCost(Product product, double costDelta) {
        Long id = product.getId();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("Update public.products SET cost= cost + :cost WHERE id=:id")
                    .setParameter("cost", costDelta, DoubleType.INSTANCE)
                    .setParameter("id", id, LongType.INSTANCE).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
