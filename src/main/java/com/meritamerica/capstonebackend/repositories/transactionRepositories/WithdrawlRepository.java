package com.meritamerica.capstonebackend.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.capstonebackend.models.transactions.Transfer;
import com.meritamerica.capstonebackend.models.transactions.Withdrawl;

public interface WithdrawlRepository extends TransactionRepository<Withdrawl, Integer> {

}
