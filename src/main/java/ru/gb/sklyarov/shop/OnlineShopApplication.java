package ru.gb.sklyarov.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineShopApplication {

    private static SessionFactoryShop sessionFactory;

    @Autowired
    public OnlineShopApplication(SessionFactoryShop sessionFactory) {
        OnlineShopApplication.sessionFactory = sessionFactory;
    }

    public static void main(String[] args) {
            SpringApplication.run(OnlineShopApplication.class, args);
    }
}
