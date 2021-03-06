package com.meritamerica.capstonebackend.models.transactions;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.capstonebackend.models.BankAccount;
import com.meritamerica.capstonebackend.models.CDAccount;

@Entity
public class Deposit extends Transaction {
	
	public Deposit() {
		this.setTransactionType("deposit");
	}
	
	public Deposit(Integer amount) {
		this.setTransactionType("withdrawl");
		this.setAmount((double) amount);
	}

	public Deposit(Integer amount, CDAccount thisCDAccount) {
		this.setTransactionType("withdrawl");
		this.setAmount((double) amount);
		super.setAccount(thisCDAccount);
	}
	
}
