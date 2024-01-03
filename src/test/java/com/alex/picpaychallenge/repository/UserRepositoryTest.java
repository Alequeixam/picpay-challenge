package com.alex.picpaychallenge.repository;

import com.alex.picpaychallenge.domain.user.TypeOfUser;
import com.alex.picpaychallenge.domain.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    public static final long ID = 1L;
    public static final String NAME = "jorge";
    private static final String DOCUMENT = "12345678910";
    private static final String EMAIL = "jorge@gmail.com";
    public static final String PASSWORD = "senha123";
    public static final TypeOfUser TYPE_OF_USER = TypeOfUser.COMMON;
    public static final BigDecimal BALANCE = new BigDecimal(100);
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("User with specific email exists")
    void existsByEmailSuccess() {
        startUser();
        var foundUser = userRepository.existsByEmail(EMAIL);
        assertTrue(foundUser);
    }
    @Test
    @DisplayName("User with specific email does not exist")
    void existsByEmailFail() {
        startUser();
        var foundUser = userRepository.existsByEmail("alex@gmail.com");
        assertFalse(foundUser);
    }

    @Test
    @DisplayName("User with specific document exists")
    void existsByDocumentSuccess() {
        startUser();
        var foundUser = userRepository.existsByDocument(DOCUMENT);
        assertTrue(foundUser);
    }
    @Test
    @DisplayName("User with specific document does not exist")
    void existsByDocumentFail() {
        startUser();
        var foundUser = userRepository.existsByDocument("98765432155");
        assertFalse(foundUser);
    }


    void startUser() {
        User savedUser = new User(ID, NAME, DOCUMENT, EMAIL, PASSWORD, TYPE_OF_USER, BALANCE);
        userRepository.save(savedUser);
    }
}