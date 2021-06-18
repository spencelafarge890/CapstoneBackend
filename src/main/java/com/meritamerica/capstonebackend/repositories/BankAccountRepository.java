package com.meritamerica.capstonebackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.BankAccount;
import com.meritamerica.capstonebackend.models.SavingsAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
	
	List<BankAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);

}
