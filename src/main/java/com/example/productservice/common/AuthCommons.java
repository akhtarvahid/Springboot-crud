package com.example.productservice.common;

import com.example.productservice.dtos.userServiceConnection.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto authenticate(String token) {

        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity("http://localhost:8081/api/users/validate/" + token, UserDto.class);

        if(responseEntity.getBody() == null) {
            // Token is invalid
            // Throw some exception
            return null;

        }
        return responseEntity.getBody();
    }
}
