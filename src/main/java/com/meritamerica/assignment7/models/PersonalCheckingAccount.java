package com.meritamerica.assignment7.models;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.assignment7.services.MeritBankService;

@Entity
public class PersonalCheckingAccount extends CheckingAccount{
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "accountHolder_id", referencedColumnName = "id")
	@JsonIgnore
	private AccountHolder accountHolder;
	
	public PersonalCheckingAccount() {
		this.interestRate = MeritBankService.getCheckingInterest();
	}
	
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}

}
