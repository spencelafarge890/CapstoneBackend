package com.meritamerica.capstonebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.capstonebackend.models.MeritBankUser;

public interface MeritBankUserRepository extends JpaRepository<MeritBankUser, Integer>{

	public MeritBankUser findByUsername(String username);

	public boolean existsMeritBankUserByUsername(String username);
	
}
