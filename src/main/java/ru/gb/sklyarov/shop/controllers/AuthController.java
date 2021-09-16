package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.sklyarov.shop.dtos.AuthRequest;
import ru.gb.sklyarov.shop.dtos.AuthResponse;
import ru.gb.sklyarov.shop.dtos.UserDto;
import ru.gb.sklyarov.shop.exceptions.DataValidationException;
import ru.gb.sklyarov.shop.exceptions.ShopAuthException;
import ru.gb.sklyarov.shop.exceptions.ShopError;
import ru.gb.sklyarov.shop.services.UserService;
import ru.gb.sklyarov.shop.utils.JwtTokenUtil;

import javax.security.auth.message.AuthException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new ShopError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String token = jwtTokenUtil.generateToken(userService.loadUserByUsername(authRequest.getUsername()));
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/registration")
    public UserDto userRegistration(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }

        try {
            return userService.registration(userDto);
        } catch (AuthException e) {
            throw new ShopAuthException(List.of(e.getMessage()));
        }
    }
}
