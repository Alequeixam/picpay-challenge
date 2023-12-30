package com.alex.picpaychallenge.domain.user.dto;

import com.alex.picpaychallenge.domain.user.TypeOfUser;

import java.math.BigDecimal;

public record UserDTO(String name,
                      String document,
                      String email,
                      String password,
                      TypeOfUser typeOfUser,
                      BigDecimal balance){

}
