package com.alex.picpaychallenge.service;

import com.alex.picpaychallenge.domain.User;
import com.alex.picpaychallenge.domain.dto.UserDTO;
import com.alex.picpaychallenge.domain.dto.UserResponse;
import com.alex.picpaychallenge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse createUser(UserDTO userDTO) {
        var user = new User(userDTO);
        repository.save(user);
        return mapToResponse(user);
    }

    public UserResponse updateUser(UserDTO userDTO, Long id) {
        var user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user = new User(userDTO);
        repository.save(user);

        return mapToResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        var users = repository.findAll();
        return users.stream().map(user -> mapToResponse(user)).toList();
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
}
