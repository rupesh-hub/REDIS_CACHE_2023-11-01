package com.example.demo.model;

import com.example.demo.entity.User;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("users")
public class UserDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();

    }
}
