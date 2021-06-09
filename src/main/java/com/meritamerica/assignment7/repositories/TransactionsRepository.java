package com.meritamerica.assignment7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.transactions.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Integer> {

}
