package ru.gb.sklyarov.shop.order.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NamedEntityGraph(
        name = "orders.for-front",
        attributeNodes = {
                @NamedAttributeNode(value = "orderItems", subgraph = "items-products")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "items-products",
                        attributeNodes = {
                                @NamedAttributeNode("productId")
                        }
                )
        }
)
public class Order {
    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    List<OrderItem> orderItems;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "is_paid")
    private boolean isPaid;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "user_id")
    private Long userId;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
