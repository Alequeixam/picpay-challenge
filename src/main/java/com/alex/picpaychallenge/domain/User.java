package com.alex.picpaychallenge.domain;

import com.alex.picpaychallenge.domain.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private TypeOfUser typeOfUser;
    private BigDecimal balance;

    public User(UserDTO dto) {
        this.name = dto.name();
        this.document = dto.document();
        this.email = dto.email();
        this.password = dto.password();
        this.typeOfUser = dto.typeOfUser();
        this.balance = dto.balance();
    }
}
