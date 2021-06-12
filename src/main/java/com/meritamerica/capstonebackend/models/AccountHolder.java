package com.meritamerica.capstonebackend.models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class AccountHolder implements Comparable<AccountHolder>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Max(250000)
	@Column(name = "combined_balance")
	private double combinedBalance;
	
	@OneToOne
	@JoinColumn(name = "id", referencedColumnName = "id")
	private AccountHolderContact accHolderCont;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
	private List<DBACheckingAccount> dbaCheckingAccounts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
	private List<PersonalCheckingAccount> personalCheckingAccounts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
	private List<SavingsAccount> savingsAccounts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
	private List<CDAccount> cdAccounts;

	@NotBlank(message="First name field must not be blank")
	@NotNull(message="First name field must not be blank")
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name")
	@NotBlank(message="Last name field must not be blank")
	@NotNull(message="Last name field must not be blank")
	private String lastName;
	
	@NotBlank(message="SSN field must not be blank")
	@NotNull(message="SSN field must not be blank")
	@Column(name = "ssn")
	private String ssn;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private MeritBankUser mbUser;
	
	public MeritBankUser getMbUser() {
		return mbUser;
	}

	public void setMbUser(MeritBankUser mbUser) {
		this.mbUser = mbUser;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public AccountHolder() {
	}
	
	public AccountHolderContact getAccHolderCont() {
		return accHolderCont;
	}

	public void setAccHolderCont(AccountHolderContact accHolderCont) {
		this.accHolderCont = accHolderCont;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCombinedBalance() {
		return combinedBalance;
	}

	public void setCombinedBalance(double combinedBalance) {
		this.combinedBalance = combinedBalance;
	}

	public List<DBACheckingAccount> getDbaCheckingAccounts() {
		return dbaCheckingAccounts;
	}

	public void setDbaCheckingAccounts(List<DBACheckingAccount> dbaCheckingAccounts) {
		this.dbaCheckingAccounts = dbaCheckingAccounts;
	}

	public List<PersonalCheckingAccount> getPersonalCheckingAccounts() {
		return personalCheckingAccounts;
	}

	public void setPersonalCheckingAccounts(List<PersonalCheckingAccount> personalCheckingAccounts) {
		this.personalCheckingAccounts = personalCheckingAccounts;
	}

	public List<SavingsAccount> getSavingsAccounts() {
		return savingsAccounts;
	}

	public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {
		this.savingsAccounts = savingsAccounts;
	}

	public List<CDAccount> getCdAccounts() {
		return cdAccounts;
	}

	public void setCdAccounts(List<CDAccount> cdAccounts) {
		this.cdAccounts = cdAccounts;
	}

	@Override
	public String toString() {
		return lastName + "," + middleName + "," + firstName + "," + ssn;
	}

	@Override
	public int compareTo(AccountHolder o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
