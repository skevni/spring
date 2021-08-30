package ru.gb.sklyarov.shop.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.dtos.ProductDto;

@NoArgsConstructor
@Getter
public class CartsContent {
    private ProductDto productDto;
    private int amount;

    public CartsContent(ProductDto productDto, int amount) {
        this.productDto = productDto;
        this.amount = amount;
    }
}
