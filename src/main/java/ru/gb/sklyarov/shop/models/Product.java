package ru.gb.sklyarov.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
// Using @Data for JPA entities is not recommended. It can cause severe performance and memory consumption issues.
// Если не рекомендуется использовать @Data для сущностей, нужно самими создавать конструкторы и геттеры с сеттерами,
// а также переопределять toString? Мы применяли аннотацию @Data в учебных целях и для продуктовых проектов так лучше не
// делать?
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

}
