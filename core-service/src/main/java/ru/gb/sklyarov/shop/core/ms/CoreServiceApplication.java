package ru.gb.sklyarov.shop.core.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("secret.properties")
public class CoreServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreServiceApplication.class, args);
    }
}
