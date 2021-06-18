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

@Entity
public class Withdrawl extends Transaction {
	
	public Withdrawl() {
		this.setTransactionType("withdrawl");
	}

	public Withdrawl(Integer amount) {
		this.setTransactionType("withdrawl");
		this.setAmount((double) amount);
	}

	public Withdrawl(Integer amount, BankAccount tempAccount) {
		this.setTransactionType("withdrawl");
		this.setAmount((double) amount);
		super.setAccount(tempAccount);
	}

	public Withdrawl(Integer amount, BankAccount tempAccount, String string) {
		this.setTransactionType("withdrawl");
		this.setAmount((double) amount);
		this.setAccount(tempAccount);
		this.setOrigin(string);
	}
}
