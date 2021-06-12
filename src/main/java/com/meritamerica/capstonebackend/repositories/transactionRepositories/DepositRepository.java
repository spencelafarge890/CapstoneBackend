package com.meritamerica.capstonebackend.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.capstonebackend.models.transactions.Deposit;

public interface DepositRepository extends TransactionRepository<Deposit, Integer> {


}
