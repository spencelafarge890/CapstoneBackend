package com.meritamerica.capstonebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meritamerica.capstonebackend.models.MeritBankUser;
import com.meritamerica.capstonebackend.models.exceptions.NoSuchResourceFoundException;
import com.meritamerica.capstonebackend.repositories.MeritBankUserRepository;


@Service
public class MeritUserDetailsService implements UserDetailsService {

	@Autowired
	private MeritBankUserRepository mbUserRepository;
	
	public List<MeritBankUser> getAllUsers() {
		return mbUserRepository.findAll();
	}
	
	public MeritBankUser loadUserByUsername(String userName) throws UsernameNotFoundException {
		if (!mbUserRepository.existsMeritBankUserByUsername(userName)) {
			throw new UsernameNotFoundException("Username " + userName + " not found.");
		} else {
			return mbUserRepository.findByUsername(userName);
		}
	}
		
	public void addMeritBankUser(MeritBankUser mbUser) {
		mbUserRepository.save(mbUser);
	}
	
	public boolean deleteById(int userId) {
		boolean isDeleted;
		try {
			mbUserRepository.deleteById(userId);
			isDeleted = true;
		} catch (Exception ex) {
			isDeleted = false;
		}
		
		return isDeleted;
	}
		
	public boolean isAdmin(MeritBankUser mbUser) {
		String role = mbUser.getRole();
		if (role.equalsIgnoreCase("admin")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isUser(MeritBankUser mbUser) {
		String role = mbUser.getRole();
		if (role.equalsIgnoreCase("user")) {
			return true;
		} else {
			return false;
		}
	}

}
