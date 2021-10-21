package ru.gb.sklyarov.shop.core.ms.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@NamedEntityGraph(name = "users.for-dto",
        attributeNodes = {
                @NamedAttributeNode(value = "roles", subgraph = "user-authorities")
        }, subgraphs = {
        @NamedSubgraph(name = "user-authorities", attributeNodes = {
                @NamedAttributeNode("authorities")
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @Range(min = 3, max = 50, message = "The name must be between 3 and 50 in length")
    private String username;

    @Column(name = "password")
    private String password;

    @Range(max = 50, message = "The maximum email length must be not exceed 50")
    @Column(name = "email")
    private String email;
    //(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
