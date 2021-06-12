package com.meritamerica.capstonebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.MeritBankUser;


public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {


	AccountHolder findByMbUser(MeritBankUser mbUser);
	
}
