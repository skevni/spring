package ru.gb.sklyarov.shop.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gb.sklyarov.shop.configs.JwtRequestFilter;
import ru.gb.sklyarov.shop.services.UserService;
import ru.gb.sklyarov.shop.utils.JwtTokenUtil;

import java.util.Collections;

@SpringBootTest
public class JwtFilterTest {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private UserService userService;
//    private

//    @BeforeEach
//    public void init() {
//        SecurityContextHolder.getContext().setAuthentication(null);
//    }
    @Test
    public void testJwtFilter() throws Exception {
        UserDetails userDetails = new User("test-user","test-password",Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        String token = jwtTokenUtil.generateToken(userDetails);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer " + token);
        request.setRequestURI("/api/v1/orders");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();
        jwtRequestFilter.doFilter(request, response, filterChain);
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("test-user");
        Assertions.assertThat(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString()).isEqualTo(token);
    }
}
