package com.meritamerica.assignment7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;

public interface PersonalCheckingAccountRepository extends JpaRepository<PersonalCheckingAccount, Integer>{
	List<PersonalCheckingAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
}
