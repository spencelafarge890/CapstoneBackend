package com.meritamerica.capstonebackend.models;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.capstonebackend.services.MeritBankService;

@Entity
public class DBACheckingAccount extends CheckingAccount {
	
	
	
	
	public DBACheckingAccount() {
		this.interestRate = MeritBankService.getCheckingInterest();
	}
	

}
