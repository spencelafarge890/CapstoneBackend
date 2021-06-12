package com.meritamerica.capstonebackend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.BankAccount;
import com.meritamerica.capstonebackend.models.CDAccount;
import com.meritamerica.capstonebackend.models.DBACheckingAccount;
import com.meritamerica.capstonebackend.models.MeritBankUser;
import com.meritamerica.capstonebackend.models.PersonalCheckingAccount;
import com.meritamerica.capstonebackend.models.SavingsAccount;
import com.meritamerica.capstonebackend.models.exceptions.ExceedsAvailableBalanceException;
import com.meritamerica.capstonebackend.models.exceptions.NoSuchResourceFoundException;
import com.meritamerica.capstonebackend.models.transactions.Transaction;
import com.meritamerica.capstonebackend.services.AccountHolderServiceImpl;
import com.meritamerica.capstonebackend.services.BankAccountServiceImpl;
import com.meritamerica.capstonebackend.services.MeritBankServiceImpl;
import com.meritamerica.capstonebackend.services.MeritUserDetailsService;
import com.meritamerica.capstonebackend.util.JwtUtil;

@CrossOrigin(origins = "*")
@RestController
public class MeEndpointController {
	
	@Autowired
	MeritUserDetailsService meritUserSvc;
	
	@Autowired
	MeritBankServiceImpl meritBankSvc;
	
	@Autowired
	AccountHolderServiceImpl accountHolderSvc;
	
	@Autowired
	BankAccountServiceImpl bankAccService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/me")
	//@Secured("ROLE_USER")
	public AccountHolder getAccountHolderByUserJWT() throws NoSuchResourceFoundException {
		//return meritBankSvc.getAccountHolderByUser(meritUserSvc.loadUserByUsername("user"));
		return meritBankSvc.getAccountHolderByUser(jwtUtil.getCurrentUser());
	}
	
	
	//Just in case we need it - otherwise this functionality will be distributed to admin role
	/*@PostMapping("/me/dba-checking-accounts")
	@Secured("ROLE_USER")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount postCheckingAccount(@RequestBody @Valid CheckingAccount checkAccount) {
		AccountHolder accHolder = jwtUtil.getAssociatedAccountHolder();
		checkAccount.setAccountHolder(accHolder);
		accountHolderSvc.addCheckingAccount(checkAccount, accHolder);
		return checkAccount;
	}*/
	
	@GetMapping("/me/dba-checking-accounts")
	@Secured("ROLE_USER")
	public List<DBACheckingAccount> getDBACheckingAccounts() throws NoSuchResourceFoundException {
		AccountHolder accHolder = jwtUtil.getAssociatedAccountHolder();
		if (meritBankSvc.getAccountHolderById(accHolder.getId()) != null) {
			return meritBankSvc.getAccountHolderById(accHolder.getId()).getDbaCheckingAccounts();
		} else {
			throw new NoSuchResourceFoundException("Account Holder not found!");
		}
	}
	
	@GetMapping("/me/dba-checking-accounts/{id}")
	@Secured("ROLE_USER")
	public DBACheckingAccount getDBACheckingAccountById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getDBACheckingAccountById(id) != null) {
			return accountHolderSvc.getDBACheckingAccountById(id);
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}
	
	@PostMapping("/me/dba-checking-accounts/{id}/add-transaction")
	@Secured("ROLE_USER")
	public Transaction addTransactionDBACheckingAccountById(@PathVariable int id,
			@RequestBody Transaction transaction) throws NoSuchResourceFoundException, ExceedsAvailableBalanceException {
		if (accountHolderSvc.getDBACheckingAccountById(id) != null) {
			bankAccService.addTransaction(transaction, accountHolderSvc.getDBACheckingAccountById(id));
			return transaction;
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}
	
	
	//In progress
	/*@PostMapping("/me/dba-checking-accounts/{id}/add-transfer")
	@Secured("ROLE_USER")
	public Transaction addTransferDBACheckingAccountById(@PathVariable int id,
			@RequestBody Transaction transaction, Integer toAccountNumber) throws NoSuchResourceFoundException, ExceedsAvailableBalanceException {
		if (accountHolderSvc.getDBACheckingAccountById(id) != null && accountHolderSvc.getDBACheckingAccountById(toAccountNumber) != null) {
			bankAccService.addTransaction(transaction, accountHolderSvc.getDBACheckingAccountById(id), accountHolderSvc.getDBACheckingAccountById(toAccountNumber));
			return transaction;
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}*/
	
	@GetMapping("/me/dba-checking-accounts/{id}/deposits")
	@Secured("ROLE_USER")
	public List<Transaction> getDBACheckingAccountDepositsById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getDBACheckingAccountById(id) != null) {
			BankAccount account = accountHolderSvc.getDBACheckingAccountById(id);
			return bankAccService.getDeposits(account);
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}
	
	@GetMapping("/me/dba-checking-accounts/{id}/withdrawls")
	@Secured("ROLE_USER")
	public List<Transaction> getDBACheckingAccountWithdrawlsById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getDBACheckingAccountById(id) != null) {
			BankAccount account = accountHolderSvc.getDBACheckingAccountById(id);
			return bankAccService.getWithdrawls(account);
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}
	
	
	@GetMapping("/me/personal-checking-accounts")
	@Secured("ROLE_USER")
	public List<PersonalCheckingAccount> getPersonalCheckingAccounts() throws NoSuchResourceFoundException {
		AccountHolder accHolder = jwtUtil.getAssociatedAccountHolder();
		if (meritBankSvc.getAccountHolderById(accHolder.getId()) != null) {
			return meritBankSvc.getAccountHolderById(accHolder.getId()).getPersonalCheckingAccounts();
		} else {
			throw new NoSuchResourceFoundException("Account Holder not found!");
		}
	}
	
	@GetMapping("/me/personal-checking-accounts/{id}")
	@Secured("ROLE_USER")
	public PersonalCheckingAccount getPersonalCheckingAccountById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getPersonalCheckingAccountById(id) != null) {
			return accountHolderSvc.getPersonalCheckingAccountById(id);
		} else {
			throw new NoSuchResourceFoundException("Account Holder not found!");
		}
	}
	
	@PostMapping("/me/personal-checking-accounts/{id}/add-transaction")
	@Secured("ROLE_USER")
	public Transaction addTransactionPersonalCheckingAccountById(@PathVariable int id,
			@RequestBody Transaction transaction) throws NoSuchResourceFoundException, ExceedsAvailableBalanceException {
		if (accountHolderSvc.getPersonalCheckingAccountById(id) != null) {
			bankAccService.addTransaction(transaction, accountHolderSvc.getPersonalCheckingAccountById(id));
			return transaction;
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}
	
	
	//In progress
	/*@PostMapping("/me/personal-checking-accounts/{id}/add-transfer")
	@Secured("ROLE_USER")
	public Transaction addTransferPersonalCheckingAccountById(@PathVariable int id,
			@RequestBody Transaction transaction, BankAccount toAccount) throws NoSuchResourceFoundException, ExceedsAvailableBalanceException {
		switch (toAccount.getName()) {
		case 
		}
		if (accountHolderSvc.getPersonalCheckingAccountById(id) != null && accountHolderSvc.getPersonalCheckingAccountById(toAccountNumber) != null) {
			bankAccService.addTransaction(transaction, accountHolderSvc.getDBACheckingAccountById(id), accountHolderSvc.getDBACheckingAccountById(toAccountNumber));
			return transaction;
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}*/
	
	@GetMapping("/me/personal-checking-accounts/{id}/deposits")
	@Secured("ROLE_USER")
	public List<Transaction> getPersonalCheckingAccountDepositsById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getPersonalCheckingAccountById(id) != null) {
			BankAccount account = accountHolderSvc.getPersonalCheckingAccountById(id);
			return bankAccService.getDeposits(account);
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}
	
	@GetMapping("/me/personal-checking-accounts/{id}/withdrawls")
	@Secured("ROLE_USER")
	public List<Transaction> getPersonalCheckingAccountWithdrawlsById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getPersonalCheckingAccountById(id) != null) {
			BankAccount account = accountHolderSvc.getPersonalCheckingAccountById(id);
			return bankAccService.getWithdrawls(account);
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}
	
	//Moving this function to admin role only
	/*@PostMapping("/me/savings-accounts")
	@Secured("ROLE_USER")
	public SavingsAccount postSavingsAccount(@RequestBody @Valid SavingsAccount savingsAccount) {
		AccountHolder accHolder = jwtUtil.getAssociatedAccountHolder();
		savingsAccount.setAccountHolder(accHolder);
		accountHolderSvc.addSavingsAccount(savingsAccount, accHolder);
		return savingsAccount;
	}*/
	
	@GetMapping("/me/savings-accounts")
	@Secured("ROLE_USER")
	public List<SavingsAccount> getSavingsAccounts() throws NoSuchResourceFoundException {
		AccountHolder accHolder = jwtUtil.getAssociatedAccountHolder();
		if (meritBankSvc.getAccountHolderById(accHolder.getId()) != null) {
			return meritBankSvc.getAccountHolderById(accHolder.getId()).getSavingsAccounts();
		} else {
			throw new NoSuchResourceFoundException("Account Holder not found!");
		}
	}
	
	@GetMapping("/me/savings-accounts/{id}")
	@Secured("ROLE_USER")
	public SavingsAccount getSavingsAccountById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getSavingsAccountById(id) != null) {
			return accountHolderSvc.getSavingsAccountById(id);
		} else {
			throw new NoSuchResourceFoundException("Account Holder not found!");
		}
	}
	
	@GetMapping("/me/savings-accounts/{id}/withdrawls")
	@Secured("ROLE_USER")
	public List<Transaction> getSavingsAccountWithdrawlsById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getSavingsAccountById(id) != null) {
			return bankAccService.getWithdrawls(accountHolderSvc.getSavingsAccountById(id));
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}
	
	@GetMapping("/me/savings-accounts/{id}/deposits")
	@Secured("ROLE_USER")
	public List<Transaction> getSavingsAccountDepositsById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getSavingsAccountById(id) != null) {
			return bankAccService.getDeposits(accountHolderSvc.getSavingsAccountById(id));
		} else {
			throw new NoSuchResourceFoundException("Account not found!");
		}
	}
	
	/*@GetMapping("/me/savings-accounts/{id}/transactions")
	@Secured("ROLE_USER")
	public List<Transaction> getSavingsAccountTransactionsById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getSavingsAccountById(id) != null) {
			List<Transaction> transList = new ArrayList<Transaction>();
			transList.add((Transaction) accountHolderSvc.getSavingsAccountById(id).getDeposits());
			return transList;
		} else {
			throw new NoSuchResourceFoundException("Account Holder not found!");
		}
	}*/
	
	//Admin role only
	/*@PostMapping("/me/cd-accounts")
	@Secured("ROLE_USER")
	public CDAccount postCDAccount(@RequestBody @Valid CDAccount cdAccount) {
		AccountHolder accHolder = jwtUtil.getAssociatedAccountHolder();
		cdAccount.setAccountHolder(accHolder);
		accountHolderSvc.addCDAccount(cdAccount, accHolder);
		return cdAccount;
	}*/
	
	@GetMapping("/me/cd-accounts")
	@Secured("ROLE_USER")
	public List<CDAccount> getcdAccounts() throws NoSuchResourceFoundException {
		AccountHolder accHolder = jwtUtil.getAssociatedAccountHolder();
		if (meritBankSvc.getAccountHolderById(accHolder.getId()) != null) {
			return meritBankSvc.getAccountHolderById(accHolder.getId()).getCdAccounts();
		} else {
			throw new NoSuchResourceFoundException("Account Holder not found!");
		}
	}
	
	@GetMapping("/me/cd-accounts/{id}")
	@Secured("ROLE_USER")
	public CDAccount getCDAccountById(@PathVariable int id) throws NoSuchResourceFoundException {
		if (accountHolderSvc.getCDAccountById(id) != null) {
			return accountHolderSvc.getCDAccountById(id);
		} else {
			throw new NoSuchResourceFoundException("Account Holder not found!");
		}
	}
	
}
