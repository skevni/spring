package ru.gb.sklyarov.shop.json;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import ru.gb.sklyarov.shop.dtos.AuthRequest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class JsonTests {
    @Autowired
    private JacksonTester<AuthRequest> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword("123456");
        authRequest.setUsername("User");
        // {
        //   "password": "123456",
        //   "username": "User"
        // }

        assertThat(jackson.write(authRequest))
                .hasJsonPathStringValue("$.password")
                .extractingJsonPathStringValue("$.username").isEqualTo("User");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"password\": \"123456\",\"username\":\"ADMIN\"}";
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("ADMIN");
        authRequest.setPassword("123456");

        assertThat(jackson.parse(content)).isEqualTo(authRequest);
        assertThat(jackson.parseObject(content).getUsername()).isEqualTo("ADMIN");
    }
}
