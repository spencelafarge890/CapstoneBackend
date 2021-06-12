package com.meritamerica.capstonebackend.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.CDOffering;
import com.meritamerica.capstonebackend.models.MeritBankUser;
import com.meritamerica.capstonebackend.models.exceptions.NoSuchResourceFoundException;
import com.meritamerica.capstonebackend.repositories.AccountHolderRepository;
import com.meritamerica.capstonebackend.repositories.CDOfferingRepository;



@Service
public class MeritBankServiceImpl extends MeritBankService {
	
	@Autowired
	private AccountHolderRepository accHolderRepo;
	
	@Autowired
	private CDOfferingRepository cdOfferingRepo;
	
	public AccountHolderRepository getAccHolderRepo() {
		return accHolderRepo;
	}

	public void setAccHolderRepo(AccountHolderRepository accHolderRepo) {
		this.accHolderRepo = accHolderRepo;
	}
	
	public List<AccountHolder> getAccountHolders() {
		return accHolderRepo.findAll();
	}
	
	public AccountHolder getAccountHolderById(int id) throws NoSuchResourceFoundException {
		return accHolderRepo.findById(id).orElseThrow(() -> new NoSuchResourceFoundException("Account Holder not found by given id"));
	}
	
	public AccountHolder getAccountHolderByUser(MeritBankUser mbUser) throws NoSuchResourceFoundException {
		return accHolderRepo.findByMbUser(mbUser);
	}
	
	public void addAccountHolder(AccountHolder accountHolder) {
		accHolderRepo.save(accountHolder);
	}
	
	public void deleteAccountHolder(AccountHolder accountHolder) {
		accHolderRepo.delete(accountHolder);
	}

	public CDOfferingRepository getCdOfferingRepo() {
		return cdOfferingRepo;
	}

	public void setCdOfferingRepo(CDOfferingRepository cdOfferingRepo) {
		this.cdOfferingRepo = cdOfferingRepo;
	}
	
	public List<CDOffering> getAllCDOfferings() {
		return cdOfferingRepo.findAll();
	}
	
	public void saveCDOffering(CDOffering offering) {
		cdOfferingRepo.save(offering);
	}
	
	public CDOffering getBestCDOffering() {
		ArrayList<CDOffering> cdoList = (ArrayList<CDOffering>) cdOfferingRepo.findAll();
		Collections.sort(cdoList);
		return cdoList.get(0);
	}
	
}
