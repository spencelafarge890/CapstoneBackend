package com.meritamerica.capstonebackend.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.capstonebackend.services.MeritBankService;

@Entity
public class SavingsAccount extends BankAccount {
	
	
	public SavingsAccount() {
		this.interestRate = MeritBankService.getSavingsInterest();
	}
	

	
}

