package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class AuthRequest {
    private String username;
    private String password;
}
