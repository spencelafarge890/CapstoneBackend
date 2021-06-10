package com.meritamerica.assignment7.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.models.CDOffering;
import com.meritamerica.assignment7.repositories.CDOfferingRepository;
import com.meritamerica.assignment7.services.MeritBankService;
import com.meritamerica.assignment7.services.MeritBankServiceImpl;

@RestController
public class CDOfferingController {
	
	@Autowired
	private CDOfferingRepository cdOfferingRepo;
	
	@Autowired
	MeritBankServiceImpl meritBankSvc;
	
	@GetMapping("/cd-offerings")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
    public List<CDOffering> getCDOfferings() { 
        return meritBankSvc.getAllCDOfferings();
    }
	
	@PostMapping("/cd-offerings")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    public CDOffering postCDOffering(@Valid @RequestBody CDOffering cdOffering) {
         meritBankSvc.saveCDOffering(cdOffering);
         return cdOffering;
    }
	
}
