package ru.gb.sklyarov.shop.common.dtos;


import java.math.BigDecimal;

public class ProductDto {
    private Long id;

//    @NotNull(message = "Товар должен иметь навзвание")
//    @Length(min = 3, max = 255, message = "Размер наименования должен быть в пределах 3 -255 символов")
    private String title;

//    @NotNull(message = "Товар должен иметь цену")
//    @DecimalMin(value = "0.01", message = "Цена должна быть больше или равна 0.01 руб")
    private BigDecimal price;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
