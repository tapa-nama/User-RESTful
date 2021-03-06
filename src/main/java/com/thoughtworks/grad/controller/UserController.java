package com.thoughtworks.grad.controller;

import com.thoughtworks.grad.domain.Contact;
import com.thoughtworks.grad.domain.User;
import com.thoughtworks.grad.repository.UserRepository;
import com.thoughtworks.grad.repository.impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return new ResponseEntity(userRepository.update(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        userRepository.delete(id);
    }


    @GetMapping("/api/users/{id}/contacts")
    public User findAllContacts(@PathVariable int id) {
        return userRepository.findContacts(id);
    }

    @GetMapping("/api/users/contacts")
    public Contact findContactOfUserByName(@RequestParam String userName, @RequestParam String contactName) {
        return userRepository.findContactByName(userName, contactName);
    }

    @PostMapping("/api/users/{id}/contacts")
    public ResponseEntity<User> addContract(@PathVariable int id, @RequestBody Contact contact) {
        User user = userRepository.addContact(id, contact);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @PutMapping("/api/users/{id}/contacts")
    public ResponseEntity<User> updateContactOfUser(@PathVariable int id, @RequestBody Contact contact) {
        return new ResponseEntity(userRepository.updateContact(id, contact), HttpStatus.ACCEPTED);
    }

/*    @PutMapping("/api/users/{userId}/contacts/{contactId}")
    public ResponseEntity<User> updateContactOfUser(@PathVariable int userId, @RequestBody Contact contact) {
        return new ResponseEntity(userRepository.updateContact(userId, contact), HttpStatus.ACCEPTED);
    }*/

    @DeleteMapping("/api/users/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactOfUSer(@PathVariable int userId, @PathVariable int contactId) {
        userRepository.deleteContactOfUser(userId, contactId);
    }


}
