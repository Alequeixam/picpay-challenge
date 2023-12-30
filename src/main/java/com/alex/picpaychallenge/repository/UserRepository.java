package com.alex.picpaychallenge.repository;

import com.alex.picpaychallenge.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
