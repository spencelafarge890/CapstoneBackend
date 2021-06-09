package com.meritamerica.assignment7.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.DBACheckingAccount;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.models.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment7.repositories.CDAccountRepository;
import com.meritamerica.assignment7.repositories.DBACheckingAccountRepository;
import com.meritamerica.assignment7.repositories.PersonalCheckingAccountRepository;
import com.meritamerica.assignment7.repositories.SavingsAccountRepository;

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
	
	public void addPersonalCheckingAccount(PersonalCheckingAccount checkAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() + checkAccount.getBalance());
		checkAccount.setAccountHolder(accHolder);
		personalCheckAccRepo.save(checkAccount);
	}
	
<<<<<<< HEAD
	public void addDBACheckingAccount(DBACheckingAccount checkAccount, AccountHolder accHolder) {
=======
	public void addPersonalCheckingAccount(CheckingAccount checkAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() + checkAccount.getBalance());
		checkAccount.setAccountHolder(accHolder);
		checkAccRepo.save(checkAccount);
	}
	
	public void addCheckingAccount(CheckingAccount checkAccount, AccountHolder accHolder) {
>>>>>>> 84b2d39f8cf8075bc1c4f96b2ba3f0e3e9e0ec36
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
	
<<<<<<< HEAD
	public DBACheckingAccount getDBACheckingAccountById(int Id) throws NoSuchResourceFoundException {
		return (DBACheckingAccount) dbaCheckAccRepo.findById(Id).orElseThrow(() -> new NoSuchResourceFoundException("Account Not Found"));
=======
	public void deleteCheckingAccount(CheckingAccount checkAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() - checkAccount.getBalance());
		checkAccRepo.delete(checkAccount);
	}
	
	public CheckingAccount getCheckingAccountById(int Id) throws NoSuchResourceFoundException {
		return (CheckingAccount) checkAccRepo.findById(Id).orElseThrow(() -> new NoSuchResourceFoundException("Account Not Found"));
>>>>>>> 84b2d39f8cf8075bc1c4f96b2ba3f0e3e9e0ec36
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
}
