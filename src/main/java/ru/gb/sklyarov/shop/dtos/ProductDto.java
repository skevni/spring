package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.sklyarov.shop.models.Product;

// задел на будущее, когда будут появляться связи
@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private double price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
