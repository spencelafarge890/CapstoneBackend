package com.meritamerica.capstonebackend.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.capstonebackend.models.transactions.Transaction;

public interface TransactionRepository<T extends Transaction, Integer> extends JpaRepository<Transaction, Integer> {
	
}
