package com.alex.picpaychallenge.domain.user.dto;

import com.alex.picpaychallenge.domain.user.TypeOfUser;

import java.math.BigDecimal;

public record UserResponse(Long id,
                           String name,
                           String document,
                           String email,
                           TypeOfUser typeOfUser,
                           BigDecimal balance) {
}
