package com.alex.picpaychallenge.repository;

import com.alex.picpaychallenge.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
