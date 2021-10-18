package ru.gb.sklyarov.shop.core.ms.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.sklyarov.shop.core.ms.entities.User;
import ru.gb.sklyarov.shop.core.ms.services.UserService;
import ru.gb.sklyarov.shop.common.dtos.UserDto;
import ru.gb.sklyarov.shop.core.ms.utils.EntityConverter;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final int PAGE_SIZE = 10;
    private final UserService userService;
    private final EntityConverter converter;

    @GetMapping("/user_profile")
    public User showUserProfile(Principal principal) {
        return userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to find user by username: %s", principal.getName())));
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
