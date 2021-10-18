package ru.gb.sklyarov.shop.core.ms.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.sklyarov.shop.core.ms.entities.Role;
import ru.gb.sklyarov.shop.core.ms.entities.User;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT r FROM Role r WHERE r.name='ROLE_USER'")
    Collection<Role> findDefaultRole();
}
