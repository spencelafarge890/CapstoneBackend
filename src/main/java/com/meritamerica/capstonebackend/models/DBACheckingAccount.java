package com.meritamerica.capstonebackend.models;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.capstonebackend.services.MeritBankService;

@Entity
public class DBACheckingAccount extends CheckingAccount {
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "accountHolder_id", referencedColumnName = "id")
	@JsonIgnore
	private AccountHolder accountHolder;
	
	
	
	public DBACheckingAccount() {
		this.interestRate = MeritBankService.getCheckingInterest();
	}
	
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}

}
