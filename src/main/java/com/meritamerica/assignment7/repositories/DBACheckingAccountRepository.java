package com.meritamerica.assignment7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
>>>>>>> 84b2d39f8cf8075bc1c4f96b2ba3f0e3e9e0ec36

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.DBACheckingAccount;

<<<<<<< HEAD
@Repository
=======
>>>>>>> 84b2d39f8cf8075bc1c4f96b2ba3f0e3e9e0ec36
public interface DBACheckingAccountRepository extends JpaRepository<DBACheckingAccount, Integer> {
	List<DBACheckingAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
}
