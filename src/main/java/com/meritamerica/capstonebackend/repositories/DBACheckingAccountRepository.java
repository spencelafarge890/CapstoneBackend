package com.meritamerica.capstonebackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.DBACheckingAccount;

@Repository
public interface DBACheckingAccountRepository extends JpaRepository<DBACheckingAccount, Integer> {
	List<DBACheckingAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
}
