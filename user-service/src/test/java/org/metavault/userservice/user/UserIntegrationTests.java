package org.metavault.userservice.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.metavault.userservice.common.payload.response.ApiResponse;
import org.metavault.userservice.user.model.payload.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTests {

    private static final String LOOPBACK = "http://127.0.0.1";

    @LocalServerPort
    private int PORT;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("사용자 추가")
    void testRegisterUser() {

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("edward@meta-vault.or.kr");
        signUpRequest.setPassword("!Q2w3e4r5t");
        signUpRequest.setFirstName("Edward");
        signUpRequest.setLastName("Kim");
        signUpRequest.setPhone("010-1234-5678");

        HttpEntity<SignUpRequest> entity = new HttpEntity<>(signUpRequest);

        ResponseEntity<String> response = restTemplate.exchange(LOOPBACK + ":" + PORT + "/api/v1/auth/signup", HttpMethod.POST, entity, String.class);

        ApiResponse apiResponse = jsonToObject(response.getBody(), ApiResponse.class);

        Assertions.assertEquals("User registerd successfully", apiResponse.getMessage());
    }

    private <T> T jsonToObject(String json, Class<T> clazz) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}