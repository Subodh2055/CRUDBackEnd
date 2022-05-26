package com.example.realworldbackend.controller;

import com.example.realworldbackend.model.User;
import com.example.realworldbackend.repository.UserRepository;
import com.example.realworldbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    @RequestMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws UserPrincipalNotFoundException {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    User updateUser(@RequestBody User user, @PathVariable Long id){
        return userRepository.findById(id).map(user1 -> {
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setPhone(user.getPhone());
            user1.setDob(user.getDob());
            user1.setAddress(user.getAddress());
            user1.setImgUrl(user.getImgUrl());
            return userRepository.save(user1);
        }).orElseGet(()-> {
            user.setId(id);
            return userRepository.save(user);
        });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
