package com.example.demo.resource;

import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import com.example.demo.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> allUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/saveAll")
    public void saveUsers() {
        userService.addUsers();
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>("user update success!!!", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveNew(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>("user created success!!!", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> saveNew(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("user deleted success!!!", HttpStatus.OK);
    }

}
