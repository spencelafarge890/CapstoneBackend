package com.meritamerica.capstonebackend.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.AuthenticationRequest;
import com.meritamerica.capstonebackend.models.AuthenticationResponse;
import com.meritamerica.capstonebackend.models.CDAccount;
import com.meritamerica.capstonebackend.models.CDAccountCreator;
import com.meritamerica.capstonebackend.models.CDOffering;
import com.meritamerica.capstonebackend.models.CheckingAccount;
import com.meritamerica.capstonebackend.models.DBACheckingAccount;
import com.meritamerica.capstonebackend.models.MeritBankUser;
import com.meritamerica.capstonebackend.models.PersonalCheckingAccount;
import com.meritamerica.capstonebackend.models.SavingsAccount;
import com.meritamerica.capstonebackend.models.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.capstonebackend.models.exceptions.NegativeAmountException;
import com.meritamerica.capstonebackend.models.exceptions.NoSuchResourceFoundException;
import com.meritamerica.capstonebackend.services.AccountHolderServiceImpl;
import com.meritamerica.capstonebackend.services.MeritBankServiceImpl;
import com.meritamerica.capstonebackend.services.MeritUserDetailsService;
import com.meritamerica.capstonebackend.util.JwtUtil;

@CrossOrigin
@RestController
public class MeritBankController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	MeritBankServiceImpl meritBankSvc;
	
	@Autowired
	AccountHolderServiceImpl accountHolderSvc;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	MeritUserDetailsService meritUserDetailService;
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String welcomeMessage() {
		return "Welcome to Merit Bank!";
	}
	
	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public List<MeritBankUser> getAllUsers() {
		return meritUserDetailService.getAllUsers();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		final MeritBankUser userDetails = (MeritBankUser) meritUserDetailService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);
		 
		return ResponseEntity.ok(new AuthenticationResponse(userDetails.getUsername(), userDetails.getRole(), jwt));
	}
	
	
	@PostMapping("/account-holders")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public AccountHolder postAccountHolder(@RequestBody @Valid AccountHolder accHolder ) {
		MeritBankUser user = meritUserDetailService.loadUserByUsername(accHolder.getMbUser().getUsername());
		accHolder.setMbUser(user);
		meritBankSvc.addAccountHolder(accHolder);
		return accHolder;
	}
	
	@PostMapping("/account-holders/delete")
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	@Secured("ROLE_ADMIN")
	public AccountHolder deleteAccountHolder(@RequestBody @Valid AccountHolder accHolder ) {
		meritBankSvc.deleteAccountHolder(accHolder);
		return accHolder;
	}
	
	@GetMapping(value = "/account-holders")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public List<AccountHolder> getAccountHolders() {
		return meritBankSvc.getAccountHolders();
	}
	
	@GetMapping("/account-holders/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public AccountHolder getAccountHolderById(@PathVariable int id) throws NoSuchResourceFoundException{
		return meritBankSvc.getAccountHolderById(id);
	}
	
	@PostMapping("/account-holders/{id}/dba-checking-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public DBACheckingAccount postDBACheckingAccount(
			@PathVariable int id, @RequestBody @Valid DBACheckingAccount checkingAccount) 
					throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder accHolder = meritBankSvc.getAccountHolderById(id);
		checkingAccount.setAccountHolder(accHolder);
		System.out.println(checkingAccount.getAccountHolder());
		accountHolderSvc.addDBACheckingAccount(checkingAccount, accHolder);
		return checkingAccount;
	}
	

	@PostMapping("/account-holders/{id}/dba-checking-accounts/delete")
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	@Secured("ROLE_ADMIN")
	public void deleteDBACheckingAccount(
			@PathVariable int id, @RequestBody @Valid DBACheckingAccount checkingAccount) 
					throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder accHolder = meritBankSvc.getAccountHolderById(id);
		accountHolderSvc.deleteDBACheckingAccount(checkingAccount, accHolder);
	}

	@GetMapping("/account-holders/{id}/dba-checking-accounts")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public List<DBACheckingAccount> getDBACheckingAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException {
		return accountHolderSvc.getDBACheckingAccountsByAccountHolder(
				meritBankSvc.getAccountHolderById(id));
	}
	
	@PostMapping("/account-holders/{id}/personal-checking-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public PersonalCheckingAccount postPersonalCheckingAccount(
			@PathVariable int id, @RequestBody @Valid PersonalCheckingAccount checkingAccount) 
					throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder accHolder = meritBankSvc.getAccountHolderById(id);
		checkingAccount.setAccountHolder(accHolder);
		System.out.println(checkingAccount.getAccountHolder());
		accountHolderSvc.addPersonalCheckingAccount(checkingAccount, accHolder);
		return checkingAccount;
	}
	
	@PostMapping("/account-holders/{id}/personal-checking-accounts/delete")
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	@Secured("ROLE_ADMIN")
	public void deletePersonalCheckingAccount(
			@PathVariable int id, @RequestBody @Valid PersonalCheckingAccount checkingAccount) 
					throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder accHolder = meritBankSvc.getAccountHolderById(id);
		accountHolderSvc.deletePersonalCheckingAccount(checkingAccount, accHolder);
	}
	
	@GetMapping("/account-holders/{id}/personal-checking-accounts")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public List<PersonalCheckingAccount> getPersonalCheckingAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException {
		return accountHolderSvc.getPersonalCheckingAccountsByAccountHolder(
				meritBankSvc.getAccountHolderById(id));
	}
	
	@PostMapping("/account-holders/{id}/savings-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public SavingsAccount postSavingsAccount(
			@PathVariable int id, @RequestBody @Valid SavingsAccount savingsAccount ) 
					throws NoSuchResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException {
		AccountHolder accHolder = meritBankSvc.getAccountHolderById(id);
		savingsAccount.setAccountHolder(accHolder);
		accountHolderSvc.addSavingsAccount(savingsAccount, accHolder);
		return savingsAccount;
	}
	
	@PostMapping("/account-holders/{id}/savings-accounts/delete")
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	@Secured("ROLE_ADMIN")
	public SavingsAccount deleteSavingsAccount(
			@PathVariable int id, @RequestBody @Valid SavingsAccount savingsAccount ) 
					throws NoSuchResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException {
		AccountHolder accHolder = meritBankSvc.getAccountHolderById(id);
		accountHolderSvc.deleteSavingsAccount(savingsAccount, accHolder);
		return savingsAccount;
	}
	
	@GetMapping("/account-holders/{id}/savings-accounts")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public List<SavingsAccount> getSavingsAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException{
		return accountHolderSvc.getSavingsAccountsByAccountHolder(
				meritBankSvc.getAccountHolderById(id));
	}
	
	
	@PostMapping("/account-holders/{id}/cd-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public CDAccount postCDAccount(
			@PathVariable int id, @RequestBody @Valid CDAccountCreator cdAccountCreator)
					throws NoSuchResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException {
		CDAccount newAccount = new CDAccount(cdAccountCreator.getCdOffering(),
				cdAccountCreator.getBalance());
		accountHolderSvc.addCDAccount(newAccount, getAccountHolderById(id));
		return newAccount;
	}
	
	@GetMapping("/account-holders/{id}/cd-accounts")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public List<CDAccount> getCDAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException{
		return accountHolderSvc.getCDAccountsByAccountHolder(
				meritBankSvc.getAccountHolderById(id));
	}
	
	@PostMapping("/account-holders/{id}/cd-accounts/delete")
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	@Secured("ROLE_ADMIN")
	public CDAccount deleteCDAccount(
			@PathVariable int id, @RequestBody @Valid CDAccount cdAccount ) 
					throws NoSuchResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException {
		AccountHolder accHolder = meritBankSvc.getAccountHolderById(id);
		accountHolderSvc.deleteCDAccount(cdAccount, accHolder);
		return cdAccount;
	}
	
	

}
