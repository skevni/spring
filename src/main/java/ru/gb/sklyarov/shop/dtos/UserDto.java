package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.entities.Role;
import ru.gb.sklyarov.shop.entities.User;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String confirmation;
    private String email;
    private String authorities;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.confirmation = user.getPassword();
        this.email = user.getEmail();

        StringBuilder role_list = new StringBuilder();
        for (Role role : user.getRoles()) {
            role_list.append(role.getName()).append(',');
        }
        for (Role role : user.getRoles()) {
            role.getAuthorities().forEach(authority -> role_list.append(authority.getName()).append(','));
        }
        role_list.setLength(role_list.length() - 1);

        this.authorities = role_list.toString();
    }
}
