package ru.gb.sklyarov.shop.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.sklyarov.shop.dtos.ProductDto;
import ru.gb.sklyarov.shop.services.ProductService;

import java.util.Optional;

@SpringBootTest
public class ProductControllerTest {
    @MockBean
    private ProductService productService;

    @Test
    public void getProductById() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("Title1");
        productDto.setPrice(0.01);

        Mockito.doReturn(Optional.of(productDto)).when(productService).findById(1L);
    }
}
