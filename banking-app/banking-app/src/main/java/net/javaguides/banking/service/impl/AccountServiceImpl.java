package net.javaguides.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepositiry;
import net.javaguides.banking.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService {
  
	
	private AccountRepositiry accountRepository;
	
	
	public AccountServiceImpl(AccountRepositiry accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		
		Account saveAccount= accountRepository.save(account);
		// TODO Auto-generated method stub
		return AccountMapper.mapToAccountDto(saveAccount);
		
		
	}


	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.
				findById(id).orElseThrow(() ->new RuntimeException("no id found"));
		
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposite(Long id, double amount) {
       
		Account account = accountRepository.
				findById(id).orElseThrow(() ->new RuntimeException("no id found"));
		
      double total= account.getBlance() + amount;
      account.setBlance(total);
      Account savedAccount=accountRepository.save(account);
	return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account = accountRepository.
				findById(id).orElseThrow(() ->new RuntimeException("no id found"));
		
		if(account.getBlance()< amount) {
			throw new RuntimeException("insufficient balance");
		}
		
		double total = account.getBlance() - amount;
		account.setBlance(total);
		Account saveAccount =accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(saveAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		
		List<Account> accounts= accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
          .collect(Collectors.toList());
	}


	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		
		Account account = accountRepository.
				findById(id).orElseThrow(() ->new RuntimeException("no id found"));
		
		accountRepository.deleteById(id);
		
		
		
	}

}
