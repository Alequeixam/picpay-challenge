package com.alex.picpaychallenge.repository;

import com.alex.picpaychallenge.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByDocument(String document);
}
