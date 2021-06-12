package com.meritamerica.capstonebackend.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.capstonebackend.models.transactions.Transfer;

public interface TransferRepository extends TransactionRepository<Transfer, Integer> {

}
