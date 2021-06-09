package com.meritamerica.assignment7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.DBACheckingAccount;

public interface DBACheckingAccountRepository extends JpaRepository<DBACheckingAccount, Integer> {
	List<DBACheckingAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
}
