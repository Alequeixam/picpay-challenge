package com.alex.picpaychallenge.domain.transaction;

import com.alex.picpaychallenge.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private BigDecimal value;

    @NotBlank
    @NotNull
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @NotBlank
    @NotNull
    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    private LocalDateTime timestamp;
}
