package com.meritamerica.capstonebackend.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.CDAccount;
import com.meritamerica.capstonebackend.models.DBACheckingAccount;
import com.meritamerica.capstonebackend.models.PersonalCheckingAccount;
import com.meritamerica.capstonebackend.models.SavingsAccount;
import com.meritamerica.capstonebackend.models.exceptions.NoSuchResourceFoundException;
import com.meritamerica.capstonebackend.repositories.CDAccountRepository;
import com.meritamerica.capstonebackend.repositories.DBACheckingAccountRepository;
import com.meritamerica.capstonebackend.repositories.PersonalCheckingAccountRepository;
import com.meritamerica.capstonebackend.repositories.SavingsAccountRepository;

@Service
public class AccountHolderServiceImpl {
	
	@Autowired
	private DBACheckingAccountRepository dbaCheckAccRepo;
	
	@Autowired
	private PersonalCheckingAccountRepository personalCheckAccRepo;
	
	@Autowired
	private SavingsAccountRepository savingAccRepo;
	
	@Autowired
	private CDAccountRepository cdAccRepo;
	
	public List<DBACheckingAccount> getDBACheckingAccounts() {
		 return dbaCheckAccRepo.findAll();
	}
	
	public List<PersonalCheckingAccount> getPersonalCheckingAccounts() {
		 return personalCheckAccRepo.findAll();
	}
	
	public void addPersonalCheckingAccount(PersonalCheckingAccount checkAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() + checkAccount.getBalance());
		checkAccount.setAccountHolder(accHolder);
		personalCheckAccRepo.save(checkAccount);
	}
	
	public void addDBACheckingAccount(DBACheckingAccount checkAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() + checkAccount.getBalance());
		checkAccount.setAccountHolder(accHolder);
		dbaCheckAccRepo.save(checkAccount);
	}
	
	public void deleteDBACheckingAccount(DBACheckingAccount checkAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() - checkAccount.getBalance());
		dbaCheckAccRepo.delete(checkAccount);
	}
	
	public void deletePersonalCheckingAccount(PersonalCheckingAccount checkAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() - checkAccount.getBalance());
		personalCheckAccRepo.delete(checkAccount);
	}
	
	public DBACheckingAccount getDBACheckingAccountById(int Id) throws NoSuchResourceFoundException {
		return (DBACheckingAccount) dbaCheckAccRepo.findById(Id).orElseThrow(() -> new NoSuchResourceFoundException("Account Not Found"));
	}
	
	public PersonalCheckingAccount getPersonalCheckingAccountById(int Id) throws NoSuchResourceFoundException {
		return (PersonalCheckingAccount) personalCheckAccRepo.findById(Id).orElseThrow(() -> new NoSuchResourceFoundException("Account Not Found"));
	}
	
	public List<DBACheckingAccount> getDBACheckingAccountsByAccountHolder(AccountHolder accountHolder) {
		 return dbaCheckAccRepo.findBankAccountByAccountHolder(accountHolder);
	}
	
	public List<PersonalCheckingAccount> getPersonalCheckingAccountsByAccountHolder(AccountHolder accountHolder) {
		 return personalCheckAccRepo.findBankAccountByAccountHolder(accountHolder);
	}
	
	public List<SavingsAccount> getSavingsAccounts() {
		 return savingAccRepo.findAll();
	}
	
	public List<SavingsAccount> getSavingsAccountsByAccountHolder(AccountHolder accountHolder) {
		 return savingAccRepo.findBankAccountByAccountHolder(accountHolder);
	}
	
	public void addSavingsAccount(SavingsAccount savingsAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() + savingsAccount.getBalance());
		savingsAccount.setAccountHolder(accHolder);
		savingAccRepo.save(savingsAccount);
	}
	
	public void deleteSavingsAccount(SavingsAccount savingsAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() - savingsAccount.getBalance());
		savingAccRepo.delete(savingsAccount);
	}
	
	public SavingsAccount getSavingsAccountById(int Id) throws NoSuchResourceFoundException {
		return (SavingsAccount) savingAccRepo.findById(Id).orElseThrow(() -> new NoSuchResourceFoundException("Account Not Found"));
	}
	
	public List<CDAccount> getCDAccounts() {
		 return cdAccRepo.findAll();
	}
	
	public void addCDAccount(CDAccount cdAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() + cdAccount.getBalance());
		cdAccount.setAccountHolder(accHolder);
		cdAccRepo.save(cdAccount);
	}
	
	public CDAccount getCDAccountById(int Id) throws NoSuchResourceFoundException {
		return (CDAccount) cdAccRepo.findById(Id).orElseThrow(() -> new NoSuchResourceFoundException("Account Not Found"));
	}
	
	public List<CDAccount> getCDAccountsByAccountHolder(AccountHolder accountHolder) {
		 return cdAccRepo.findBankAccountByAccountHolder(accountHolder);
	}

	public DBACheckingAccountRepository getDBACheckAccRepo() {
		return dbaCheckAccRepo;
	}
	
	public PersonalCheckingAccountRepository getPersonalCheckAccRepo() {
		return personalCheckAccRepo;
	}

	public void setDBACheckAccRepo(DBACheckingAccountRepository checkAccRepo) {
		this.dbaCheckAccRepo = checkAccRepo;
	}
	
	public void setPersonalCheckAccRepo(PersonalCheckingAccountRepository checkAccRepo) {
		this.personalCheckAccRepo = checkAccRepo;
	}

	public SavingsAccountRepository getSavingAccRepo() {
		return savingAccRepo;
	}

	public void setSavingAccRepo(SavingsAccountRepository savingAccRepo) {
		this.savingAccRepo = savingAccRepo;
	}

	public CDAccountRepository getCdAccRepo() {
		return cdAccRepo;
	}

	public void setCdAccRepo(CDAccountRepository cdAccRepo) {
		this.cdAccRepo = cdAccRepo;
	}

	public void deleteCDAccount(@Valid CDAccount cdAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() - cdAccount.getBalance());
		cdAccRepo.delete(cdAccount);
	}
}