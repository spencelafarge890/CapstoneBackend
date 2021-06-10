package com.meritamerica.assignment7.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.transactions.Transfer;
import com.meritamerica.assignment7.models.transactions.Withdrawl;

public interface WithdrawlRepository extends TransactionRepository<Withdrawl, Integer> {

}
