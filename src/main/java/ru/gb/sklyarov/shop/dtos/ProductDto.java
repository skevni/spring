package ru.gb.sklyarov.shop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.gb.sklyarov.shop.models.Product;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotNull(message = "Товар должен иметь навзвание")
    @Length(min = 3, max = 255, message = "Размер наименования должен быть в пределах 3 -255 символов")
    private String title;

    @NotNull(message = "Товар должен иметь цену")
    @DecimalMin(value = "0.01", message = "Цена должна быть больше или равна 0.01 руб")
    private double price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
