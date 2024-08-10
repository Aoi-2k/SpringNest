package net.javaguides.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.banking.entity.Account;

public interface AccountRepositiry extends JpaRepository<Account, Long> {
	
	

}
