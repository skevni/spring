package ru.gb.sklyarov.shop.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "statistics")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "service_name")
    private String service_name;

    @Column(name = "duration")
    private long duration;
}
