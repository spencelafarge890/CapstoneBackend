package com.meritamerica.capstonebackend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.capstonebackend.models.transactions.Deposit;
import com.meritamerica.capstonebackend.models.transactions.Transaction;
import com.meritamerica.capstonebackend.models.transactions.Transfer;
import com.meritamerica.capstonebackend.models.transactions.Withdrawl;

import java.lang.NumberFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@MappedSuperclass
public abstract class BankAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Positive
	@NotNull
	@Column(name = "balance")
	protected double balance;
	
	@Column(name = "account_open_date")
	protected Date accountOpenedOn; 
	
	@Positive
	@NotNull
	@Column(name = "interest_rate")
	protected double interestRate;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "account", referencedColumnName = "id")
	private List<Transaction> transactions;
	
	
	public BankAccount() {
		this.accountOpenedOn = new Date();
		
	}
	
	public BankAccount(double openingBalance) {
		this.balance = openingBalance;
		//this.accountNumber = MeritBankService.getNextAccountNumber();
		this.accountOpenedOn = new Date();
	}
	
	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
		//MeritBankService.getNextAccountNumber();
		this.accountOpenedOn = new Date();
	}
	
	public BankAccount(double balance, double interestRate, Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		//MeritBankService.getNextAccountNumber();
		this.accountOpenedOn = accountOpenedOn;
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double intRate) {
		interestRate = intRate;
	}
	
	public Date getOpenedOn() {
		return accountOpenedOn;
	}
	
	public void setAccountOpenedOn(Date date) {
		accountOpenedOn = date;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = dateFormatter.format(accountOpenedOn);
		return id + "," + balance + "," + interestRate
				+ "," + dateString;
	}

}