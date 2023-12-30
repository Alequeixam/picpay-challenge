package com.alex.picpaychallenge.domain.transaction;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value,
                             Long payer,
                             Long payee) {
}
