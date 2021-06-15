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
@DiscriminatorValue("transfer")
public class Transfer extends Transaction {
	
	public Transfer() {
		this.setTransactionType("transfer");
	}
}
