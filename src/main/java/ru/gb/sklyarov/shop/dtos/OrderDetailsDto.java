package ru.gb.sklyarov.shop.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailsDto {
    private String phone;
    private String address;
}
