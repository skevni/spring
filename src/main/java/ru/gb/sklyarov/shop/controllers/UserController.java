package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.sklyarov.shop.entities.User;
import ru.gb.sklyarov.shop.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/userProfile")
public class UserController {
    private final UserService userService;

    @GetMapping
    public User showUserProfile(Principal principal){
        return userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("Unable to find user by username: %s", principal.getName())));
    }

}
