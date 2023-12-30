package com.alex.picpaychallenge.domain.dto;

import com.alex.picpaychallenge.domain.TypeOfUser;

import java.math.BigDecimal;

public record UserDTO(String name,
                      String document,
                      String email,
                      String password,
                      TypeOfUser typeOfUser,
                      BigDecimal balance){

}
