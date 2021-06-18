package com.meritamerica.capstonebackend.repositories.transactionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.capstonebackend.models.transactions.Transaction;
import com.meritamerica.capstonebackend.models.transactions.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}
