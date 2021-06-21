package com.meritamerica.capstonebackend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.meritamerica.capstonebackend.models.AccountHolder;
import com.meritamerica.capstonebackend.models.CDOffering;
import com.meritamerica.capstonebackend.models.exceptions.NoSuchResourceFoundException;
import com.meritamerica.capstonebackend.repositories.CDOfferingRepository;
import com.meritamerica.capstonebackend.services.MeritBankService;
import com.meritamerica.capstonebackend.services.MeritBankServiceImpl;

@CrossOrigin
@RestController
public class CDOfferingController {
	
	@Autowired
	private CDOfferingRepository cdOfferingRepo;
	
	@Autowired
	MeritBankServiceImpl meritBankSvc;
	
	@GetMapping("/cd-offerings")
	@ResponseStatus(HttpStatus.OK)
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

	@PutMapping("/cd-offerings/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<CDOffering> updateCDOffering(@PathVariable int id, @Valid @RequestBody CDOffering cdOffering) throws NoSuchResourceFoundException {
		if (cdOffering == null || cdOffering.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		
		CDOffering currentCDOffering = meritBankSvc.getCDOfferingById(id);
		currentCDOffering = cdOffering;
        meritBankSvc.saveCDOffering(currentCDOffering);
        
        return new ResponseEntity<CDOffering>(currentCDOffering, HttpStatus.OK);
	}
}
