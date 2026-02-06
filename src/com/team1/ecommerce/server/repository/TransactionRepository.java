package com.team1.ecommerce.server.repository;

import com.team1.ecommerce.shared.model.Transaction;

import java.util.List;


public interface TransactionRepository {

    /**
     * Persists a new transaction.
     *
     * @param transaction saved transaction
     */
    void save(Transaction transaction);

    /**
     * Returns all transactions.
     *
     * @return list of all transactions (may be empty, never {@code null})
     */
    List<Transaction> findAll();
}

