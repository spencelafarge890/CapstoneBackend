package com.meritamerica.assignment7.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.transactions.Transaction;

public interface TransactionRepository<T extends Transaction, Integer> extends JpaRepository<Transaction, Integer> {
	
}
