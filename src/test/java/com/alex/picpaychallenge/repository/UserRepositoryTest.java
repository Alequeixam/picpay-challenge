package com.alex.picpaychallenge.repository;

import com.alex.picpaychallenge.domain.user.TypeOfUser;
import com.alex.picpaychallenge.domain.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    private final String DOCUMENT = "12345678910";
    private final String EMAIL = "jorge@gmail.com";
    User savedUser = new User();

    @Test
    void existsByEmail() {
        User savedUser = new User(1L, "Jorge", DOCUMENT, EMAIL, "senha123", TypeOfUser.COMMON, new BigDecimal(100));
        userRepository.save(savedUser);
        var foundUser = userRepository.existsByEmail("jorge@gmail.com");
        assertTrue(foundUser);
    }

    @Test
    void existsByDocument() {
        User savedUser = new User(1L, "Jorge", DOCUMENT, EMAIL, "senha123", TypeOfUser.COMMON, new BigDecimal(100));
        userRepository.save(savedUser);
        var foundUser = userRepository.existsByDocument(DOCUMENT);
        assertTrue(foundUser);
    }
}