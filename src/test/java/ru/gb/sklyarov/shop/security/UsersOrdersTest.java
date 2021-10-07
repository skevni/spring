package ru.gb.sklyarov.shop.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.gb.sklyarov.shop.dtos.OrderDto;
import ru.gb.sklyarov.shop.entities.Order;
import ru.gb.sklyarov.shop.entities.Role;
import ru.gb.sklyarov.shop.entities.User;
import ru.gb.sklyarov.shop.services.OrderService;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersOrdersTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/api/v1/orders"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "John", roles = "USER")
    public void securityCheckUserTest() throws Exception {
        mockMvc.perform(get("/api/v1/orders"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "John", roles = "User")
    public void securityGetNotYourOrders() throws Exception {
        Principal principal = (UserPrincipal) () -> "John";

        Order order = new Order();
        order.setId(1L);
        order.setAddress("Alll");
        order.setPhone("888888");
        order.setTotalPrice(8.88);
        order.setOrderItems(Collections.emptyList());
        order.setOrderDate(LocalDateTime.of(2021,1,1,0,0,0));
        User user = new User();
        user.setId(1L);
        user.setUsername(principal.getName());
        user.setPassword("100");
        user.setEmail("www@rt.ru");

        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        user.setRoles(List.of(role));

        order.setUser(user);


        List<Order> orders = new ArrayList<>(List.of(order));

        given(orderService.findAllByUsername(principal.getName())).willReturn(orders);

        mockMvc.perform(get("/api/vi/orders").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(orders.get(0).getId())));

    }
}
