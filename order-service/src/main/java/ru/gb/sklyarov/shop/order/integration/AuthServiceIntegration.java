package ru.gb.sklyarov.shop.order.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.sklyarov.shop.common.dtos.UserDto;

@Component
public class AuthServiceIntegration {
    @Autowired
    public WebClient authServiceWebClient;

    public UserDto getUserByUsername(String username) {
        return authServiceWebClient.get().uri("/users/" + username).retrieve().bodyToMono(UserDto.class).block();
    }
}
