package ru.gb.sklyarov.shop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private LocalDateTime dateTime;

    @Column(name = "price")
    private double price;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @ManyToOne
    @JoinTable(name = "products", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Product product;

}
