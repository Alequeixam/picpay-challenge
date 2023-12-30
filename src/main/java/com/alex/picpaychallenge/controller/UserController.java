package com.alex.picpaychallenge.controller;

import com.alex.picpaychallenge.domain.User;
import com.alex.picpaychallenge.domain.dto.UserDTO;
import com.alex.picpaychallenge.domain.dto.UserResponse;
import com.alex.picpaychallenge.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(service.createUser(userDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(service.updateUser(userDTO, id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }
}
