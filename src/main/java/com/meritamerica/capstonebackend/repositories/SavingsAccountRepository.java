package com.meritamerica.capstonebackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.BankAccount;
import com.meritamerica.capstonebackend.models.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer>{

	List<SavingsAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
	
}
