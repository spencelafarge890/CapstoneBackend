package com.meritamerica.capstonebackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.BankAccount;
import com.meritamerica.capstonebackend.models.CDAccount;

@Repository
public interface CDAccountRepository extends JpaRepository<CDAccount, Integer>{
	
	List<CDAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
}
