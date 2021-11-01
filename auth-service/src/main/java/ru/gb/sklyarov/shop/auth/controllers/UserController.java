package ru.gb.sklyarov.shop.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.auth.entities.User;
import ru.gb.sklyarov.shop.auth.services.UserService;
import ru.gb.sklyarov.shop.auth.utils.EntityShopConverter;
import ru.gb.sklyarov.shop.common.dtos.UserDto;

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final int PAGE_SIZE = 10;
    private final UserService userService;
    private final EntityShopConverter converter;

    @GetMapping("/user_profile")
    public UserDto showUserProfile(@RequestHeader String username) {
        // Если страничка перезагружается без перелогирования, то славливаю NullPointerException
        // principal = null почему-то
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to find user by username: %s", username)));
        return converter.userToDto(user);
    }

    @GetMapping("/users")
    public Page<UserDto> showAllUsers(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }

        return userService.findAllUsers(pageIndex - 1, PAGE_SIZE).map(converter::userToDto);
    }

    @GetMapping("/users/{username}")
    public UserDto getUserByUsername(@PathVariable(name = "username") String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to find user by username: %s", username)));
        return converter.userToDto(user);
    }
}
