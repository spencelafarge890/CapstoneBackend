package com.meritamerica.assignment7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;

@Repository
public interface PersonalCheckingAccountRepository extends JpaRepository<PersonalCheckingAccount, Integer>{
	List<PersonalCheckingAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
}
