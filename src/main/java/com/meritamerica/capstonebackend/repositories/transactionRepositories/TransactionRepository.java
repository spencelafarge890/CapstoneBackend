package com.meritamerica.capstonebackend.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.capstonebackend.models.transactions.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
}
