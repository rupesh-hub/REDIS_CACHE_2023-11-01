package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private HashOperations<String, Long, Object> operations;

    @Cacheable(value = "users")
    public List<UserDTO> getAllUsers() {
        //PageRequest.of(1,10)
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }


    public void addUsers() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setFirstName(UUID.randomUUID().toString());
            user.setLastName(UUID.randomUUID().toString());
            user.setEmail(UUID.randomUUID() + "@gmail.com");
            user.setUsername(UUID.randomUUID().toString());
            user.setPassword(UUID.randomUUID().toString());
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    //    @CachePut(value = "users", key = "#user.id")
    @CacheEvict(value = "users", allEntries = true)
    public void addUser(final User user) {
        //add: @CachePut(value = "users", key = "#user.id")
        //add: operations.putIfAbsent("users",user.getId(), user);
        //update: operations.put("users",user.getId())
        //get record: operations.get("users", user.getId())
        //get all records: operations.entries("users")
        //remove entry: operations.delete("users", user.getId()

        userRepository.save(user);
    }

    public void updateUser(final User user) {
        final User dbUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Could not find"));

        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setEmail(user.getEmail());
        dbUser.setUsername(user.getUsername());
        dbUser.setPassword(user.getPassword());

        userRepository.save(dbUser);
    }

    @Caching(
            evict = @CacheEvict(value = "users", allEntries = true)
    )
    public void deleteUser(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find"));

        userRepository.delete(user);

    }

}
