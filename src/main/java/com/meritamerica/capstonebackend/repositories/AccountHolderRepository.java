package com.meritamerica.assignment7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.MeritBankUser;


public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {


	AccountHolder findByMbUser(MeritBankUser mbUser);
	
}
