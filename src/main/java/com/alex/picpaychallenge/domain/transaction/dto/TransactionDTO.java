package com.alex.picpaychallenge.domain.transaction.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value,
                             Long payer,
                             Long payee) {
}
