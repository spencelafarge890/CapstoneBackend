package com.meritamerica.assignment7.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.DBACheckingAccount;
import com.meritamerica.assignment7.models.MeritBankUser;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.models.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment7.services.AccountHolderServiceImpl;
import com.meritamerica.assignment7.services.MeritBankServiceImpl;
import com.meritamerica.assignment7.services.MeritUserDetailsService;
import com.meritamerica.assignment7.util.JwtUtil;

@RestController
public class MeEndpointController {
	
	@Autowired
	MeritUserDetailsService meritUserSvc;
	
	@Autowired
	MeritBankServiceImpl meritBankSvc;
	
	@Autowired
	AccountHolderServiceImpl accountHolderSvc;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/me")
	@Secured("ROLE_USER")
	public MeritBankUser getAccountHolderByUserId() {
		return jwtUtil.getCurrentUser();
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
	
}
