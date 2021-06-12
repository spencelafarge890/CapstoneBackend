package com.meritamerica.assignment7.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.transactions.Deposit;

public interface DepositRepository extends TransactionRepository<Deposit, Integer> {


}
