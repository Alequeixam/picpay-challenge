package com.alex.picpaychallenge.service;

import com.alex.picpaychallenge.domain.user.TypeOfUser;
import com.alex.picpaychallenge.domain.user.User;
import com.alex.picpaychallenge.domain.user.dto.UserDTO;
import com.alex.picpaychallenge.domain.user.dto.UserResponse;
import com.alex.picpaychallenge.exception.DocumentAlreadyExistsException;
import com.alex.picpaychallenge.exception.EmailAlreadyExistsException;
import com.alex.picpaychallenge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.save(user);
    }
    public UserResponse createUser(UserDTO userDTO) {
        if (repository.existsByEmail(userDTO.email())) {
            throw new EmailAlreadyExistsException("This email is already registered.");
        }
        if (repository.existsByDocument(userDTO.document())) {
            throw new DocumentAlreadyExistsException("This document is already registered.");
        }


        var user = new User(userDTO);
        repository.save(user);
        return mapToResponse(user);
    }

    public UserResponse updateUser(UserDTO userDTO, Long id) {
        var user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setId(id);
        user.setName(userDTO.name());
        user.setDocument(userDTO.document());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setBalance(userDTO.balance());
        user.setTypeOfUser(userDTO.typeOfUser());
        repository.save(user);

        return mapToResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        var users = repository.findAll();
        return users.stream().map(user -> mapToResponse(user)).toList();
    }

    public User findUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User does not exist in the database."));
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getName(), user.getDocument(), user.getEmail(),
                user.getPassword(), user.getTypeOfUser(), user.getBalance());
    }
//    private User mapToEntity(UserDTO userDTO) {
//        var user = new User();
//        user.setName(userDTO.name());
//        user.setDocument(userDTO.document());
//        user.setEmail(userDTO.email());
//        user.setPassword(userDTO.password());
//        user.setTypeofUser(userDTO.typeOfUser());
//        user.setBalance(userDTO.balance());
//        return user;
//    }
    private UserResponse mapToResponse (User user) {
        return new UserResponse(user.getId(), user.getName(), user.getDocument(), user.getEmail(),
                user.getTypeOfUser(), user.getBalance());
    }

    public boolean validateUser(User payee, BigDecimal value) {

        if (payee.getTypeOfUser() != TypeOfUser.COMMON) {
            throw new RuntimeException("Merchants can not make transactions.");
        }

        if (payee.getBalance().compareTo(value) < 0) {
            throw new RuntimeException("Insufficient balance on this account");
        }
        return true;
    }
}
