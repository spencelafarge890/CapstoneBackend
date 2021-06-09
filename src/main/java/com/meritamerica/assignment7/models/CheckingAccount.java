package com.meritamerica.assignment7.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.assignment7.services.MeritBankService;


@MappedSuperclass
<<<<<<< HEAD
public abstract class CheckingAccount extends BankAccount {
	
	
	
=======
public class CheckingAccount extends BankAccount {
>>>>>>> 84b2d39f8cf8075bc1c4f96b2ba3f0e3e9e0ec36
	
	
	
	
	
}
