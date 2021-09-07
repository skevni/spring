package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.sklyarov.shop.dtos.UserDto;
import ru.gb.sklyarov.shop.entities.User;
import ru.gb.sklyarov.shop.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final int PAGE_SIZE = 10;

    @GetMapping("/user_profile")
    public User showUserProfile(Principal principal) {
        return userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to find user by username: %s", principal.getName())));
    }

    @GetMapping("/users")
    public Page<UserDto> showAllUsers(@RequestParam(defaultValue = "1", name = "p") int pageIndex){
        if (pageIndex < 1){
            pageIndex = 1;
        }

        return userService.findAllUsers(pageIndex-1, PAGE_SIZE).map(UserDto::new);
    }
}
