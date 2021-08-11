package ru.gb.sklyarov.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String customerName;

    @OneToMany(mappedBy = "customer")
    List<Order> orderList;


    public Customer() {
    }
}
