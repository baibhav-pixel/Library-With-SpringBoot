package com.example.library.Repositories;

import com.example.library.Models.Transaction;
import com.example.library.Models.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findTransactionByRequest_Book_IdAndTransactionStatusOrderByTransactionDateDesc(int id, TransactionStatus transactionStatus);
}
