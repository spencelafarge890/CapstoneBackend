package com.meritamerica.assignment7.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.models.BankAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.models.exceptions.ExceedsAvailableBalanceException;
import com.meritamerica.assignment7.models.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment7.models.transactions.Deposit;
import com.meritamerica.assignment7.models.transactions.Transaction;
import com.meritamerica.assignment7.models.transactions.Transfer;
import com.meritamerica.assignment7.models.transactions.Withdrawl;
import com.meritamerica.assignment7.repositories.CDAccountRepository;
import com.meritamerica.assignment7.repositories.DBACheckingAccountRepository;
import com.meritamerica.assignment7.repositories.PersonalCheckingAccountRepository;
import com.meritamerica.assignment7.repositories.SavingsAccountRepository;
import com.meritamerica.assignment7.repositories.transactionRepositories.DepositRepository;
import com.meritamerica.assignment7.repositories.transactionRepositories.TransferRepository;
import com.meritamerica.assignment7.repositories.transactionRepositories.WithdrawlRepository;

@Service
public class BankAccountServiceImpl {
	@Autowired
	private DBACheckingAccountRepository dbaCheckingRepo;
	
	@Autowired
	private PersonalCheckingAccountRepository personalCheckRepo;
	
	@Autowired
	private SavingsAccountRepository savingsRepo;
	
	@Autowired
	private CDAccountRepository cdRepo;
	
	@Autowired
	private DepositRepository depositRepo;
	
	@Autowired 
	private TransferRepository transferRepo;
	
	@Autowired
	private WithdrawlRepository withdrawlRepo;
	
	public List<Transaction> getDeposits(BankAccount account) throws NoSuchResourceFoundException {
		List<Transaction> deposits = new ArrayList<Transaction>();
		if (account.getTransactions() != null){
			for (Transaction transaction : account.getTransactions()) {
				if (transaction.getTransactionType() == "deposit") {
					deposits.add(transaction);
				}
			}
			return deposits;
		} else {
			throw new NoSuchResourceFoundException("No transactions found for that account");
		}
	}
	
	public List<Transaction> getWithdrawls(BankAccount account) throws NoSuchResourceFoundException {
		List<Transaction> deposits = new ArrayList<Transaction>();
		if (account.getTransactions() != null){
			for (Transaction transaction : account.getTransactions()) {
				if (transaction.getTransactionType() == "withdrawl") {
					deposits.add(transaction);
				}
			}
			return deposits;
		} else {
			throw new NoSuchResourceFoundException("No transactions found for that account");
		}
	}
	
	public List<Transaction> getTransfers(BankAccount account) throws NoSuchResourceFoundException {
		List<Transaction> deposits = new ArrayList<Transaction>();
		if (account.getTransactions() != null){
			for (Transaction transaction : account.getTransactions()) {
				if (transaction.getTransactionType() == "transfer") {
					deposits.add(transaction);
				}
			}
			return deposits;
		} else {
			throw new NoSuchResourceFoundException("No transactions found for that account");
		}
	}
	
	public void addTransaction(Transaction transaction, BankAccount account) throws ExceedsAvailableBalanceException {
		switch (transaction.getTransactionType()) {
			case "withdrawl":
				addWithdrawl((Withdrawl) transaction, account);
				break;
			case "deposit":
				addDeposit((Deposit) transaction, account);
				break;
		}
	}
	
	public void addTransaction(Transaction transaction, BankAccount fromAccount, BankAccount toAccount) throws ExceedsAvailableBalanceException {
		addTransfer((Transfer) transaction, fromAccount, toAccount);
	}
	
	public void addWithdrawl(Withdrawl withdrawl, BankAccount account) throws ExceedsAvailableBalanceException {
		if (account.getBalance() >= withdrawl.getAmount()) {
		account.addTransaction(withdrawl);
		withdrawlRepo.save(withdrawl);
		account.setBalance(account.getBalance() - withdrawl.getAmount());
		} else {
			throw new ExceedsAvailableBalanceException("Cannot complete withdrawl; Insufficient funds.");
		}
	}
	
	public void addDeposit(Deposit deposit, BankAccount account) {
		account.addTransaction(deposit);
		depositRepo.save(deposit);
		account.setBalance(account.getBalance() + deposit.getAmount());
	}
	
	
	public void addTransfer(Transfer transfer, BankAccount fromAccount, BankAccount toAccount) throws ExceedsAvailableBalanceException {
		if (fromAccount.getBalance() >= transfer.getAmount()) {
		fromAccount.addTransaction(transfer);
		toAccount.addTransaction(transfer);
		transferRepo.save(transfer);
		toAccount.setBalance(toAccount.getBalance() + transfer.getAmount());
		fromAccount.setBalance(fromAccount.getBalance() - transfer.getAmount());
		} else {
			throw new ExceedsAvailableBalanceException("Cannot complete transfer; Insufficient funds");
		}
	}

	public DBACheckingAccountRepository getDbaCheckingRepo() {
		return dbaCheckingRepo;
	}

	public void setDbaCheckingRepo(DBACheckingAccountRepository dbaCheckingRepo) {
		this.dbaCheckingRepo = dbaCheckingRepo;
	}

	public PersonalCheckingAccountRepository getPersonalCheckRepo() {
		return personalCheckRepo;
	}

	public void setPersonalCheckRepo(PersonalCheckingAccountRepository personalCheckRepo) {
		this.personalCheckRepo = personalCheckRepo;
	}

	public SavingsAccountRepository getSavingsRepo() {
		return savingsRepo;
	}

	public void setSavingsRepo(SavingsAccountRepository savingsRepo) {
		this.savingsRepo = savingsRepo;
	}

	public CDAccountRepository getCdRepo() {
		return cdRepo;
	}

	public void setCdRepo(CDAccountRepository cdRepo) {
		this.cdRepo = cdRepo;
	}

	public DepositRepository getDepositRepo() {
		return depositRepo;
	}

	public void setDepositRepo(DepositRepository depositRepo) {
		this.depositRepo = depositRepo;
	}

	public TransferRepository getTransferRepo() {
		return transferRepo;
	}

	public void setTransferRepo(TransferRepository transferRepo) {
		this.transferRepo = transferRepo;
	}

	public WithdrawlRepository getWithdrawlRepo() {
		return withdrawlRepo;
	}

	public void setWithdrawlRepo(WithdrawlRepository withdrawlRepo) {
		this.withdrawlRepo = withdrawlRepo;
	}
	

}
