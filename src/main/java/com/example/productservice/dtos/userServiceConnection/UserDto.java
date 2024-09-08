package com.example.productservice.dtos.userServiceConnection;
import com.example.productservice.models.userServiceConnection.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;
}