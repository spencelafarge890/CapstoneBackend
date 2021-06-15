package com.meritamerica.capstonebackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.PersonalCheckingAccount;


@Repository
public interface PersonalCheckingAccountRepository extends JpaRepository<PersonalCheckingAccount, Integer>{
	List<PersonalCheckingAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
}
