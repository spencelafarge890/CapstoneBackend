package com.meritamerica.assignment7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.DBACheckingAccount;

@Repository
public interface DBACheckingAccountRepository extends JpaRepository<DBACheckingAccount, Integer> {
	List<DBACheckingAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
}
