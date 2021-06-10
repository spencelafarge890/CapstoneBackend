package com.meritamerica.assignment7.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.transactions.Transfer;

public interface TransferRepository extends TransactionRepository<Transfer, Integer> {

}
