package ru.gb.sklyarov.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineShopApplication {

    private static SessionFactoryProj sessionFactory;

    @Autowired
    public OnlineShopApplication(SessionFactoryProj sessionFactory) {
        OnlineShopApplication.sessionFactory = sessionFactory;
    }

    public static void main(String[] args) {
        try {
            SpringApplication.run(OnlineShopApplication.class, args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//			sessionFactory.shutdown();
//        }
    }
}
