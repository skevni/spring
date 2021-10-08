package ru.gb.sklyarov.shop.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gb.sklyarov.shop.services.UserService;
import ru.gb.sklyarov.shop.utils.JwtTokenUtil;

import java.util.Collection;
import java.util.Collections;

@SpringBootTest
public class JwtFilterTest {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private UserService userService;
//    private
    @Test
    public void testJwtFilter() throws Exception {
        UserDetails userDetails = new User("test-user","test-password",Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        String token = jwtTokenUtil.generateToken(userDetails);

        MockHttpServletRequest request = new MockHttpServletRequest();
    }
}
