package ru.gb.sklyarov.shop.auth.utils;

import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.auth.entities.Role;
import ru.gb.sklyarov.shop.auth.entities.User;
import ru.gb.sklyarov.shop.common.dtos.UserDto;

@Component
public class EntityShopConverter {
    public UserDto userToDto(User user) {
        StringBuilder role_list = new StringBuilder();
        for (Role role : user.getRoles()) {
            role_list.append(role.getName()).append(',');
        }
        for (Role role : user.getRoles()) {
            role.getAuthorities().forEach(authority -> role_list.append(authority.getName()).append(','));
        }
        role_list.setLength(role_list.length() - 1);

        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getPassword(), user.getEmail(), role_list.toString());
    }
}
