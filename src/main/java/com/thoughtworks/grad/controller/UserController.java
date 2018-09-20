package com.thoughtworks.grad.controller;

import com.thoughtworks.grad.domain.User;
import com.thoughtworks.grad.repository.UserRepository;
import com.thoughtworks.grad.repository.impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {

    private UserRepository userRepository = new UserRepositoryImpl();

    @GetMapping("/api/users")
    public Collection<User> queryUser() {
        return userRepository.findUsers();
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User user = userRepository.createUser(newUser);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

}
