package com.meritamerica.capstonebackend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.capstonebackend.models.AccountHolderContact;

public interface ContactRepo extends JpaRepository<AccountHolderContact, Integer>{

	
}
