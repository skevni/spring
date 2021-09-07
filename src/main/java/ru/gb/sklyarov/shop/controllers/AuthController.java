package ru.gb.sklyarov.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.sklyarov.shop.dtos.AuthRequest;
import ru.gb.sklyarov.shop.dtos.AuthResponse;
import ru.gb.sklyarov.shop.dtos.UserDto;
import ru.gb.sklyarov.shop.entities.Role;
import ru.gb.sklyarov.shop.entities.User;
import ru.gb.sklyarov.shop.exceptions.DataValidationException;
import ru.gb.sklyarov.shop.exceptions.ShopError;
import ru.gb.sklyarov.shop.services.UserService;
import ru.gb.sklyarov.shop.utils.JwtTokenUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new ShopError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            e.printStackTrace();
        }
        String token = jwtTokenUtil.generateToken(userService.loadUserByUsername(authRequest.getUsername()));
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/registration")
    public UserDto userRegistration(@RequestBody @Validated UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new DataValidationException(400, bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoding(userDto.getPassword()));

        Role role = new Role();
        role.setName("USER");
        user.setRoles(List.of(role));

        userService.save(user);
        return new UserDto(user);
    }
    private String passwordEncoding(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
