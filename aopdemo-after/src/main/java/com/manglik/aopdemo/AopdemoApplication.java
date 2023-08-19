package com.manglik.aopdemo;

import com.manglik.aopdemo.dao.AccountDao;
import com.manglik.aopdemo.dao.MembershipDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao, MembershipDao membershipDao){
		return runner -> {
//			demoTheBeforeAdvice(accountDao, membershipDao);
//			demoTheAfterReturningAdvice(accountDao);
//			demoTheAfterThwrowAdvice(accountDao);
			demoTheAfterAdvice(accountDao);
		};

	}

	private void demoTheAfterAdvice(AccountDao accountDao) {
		List<Account> accounts = null;
		try {
			boolean tripwire = false;

			accounts = accountDao.findAccounts(tripwire);
		}
		catch (Exception ex){
			System.out.println("Main program caught exception");
		}
		System.out.println("\n demoTheAfterThrowingAdvice");
		System.out.println(accounts);
	}

	private void demoTheAfterThwrowAdvice(AccountDao accountDao) {
		List<Account> accounts = null;
		try {
			boolean tripwire = true;

			accounts = accountDao.findAccounts(tripwire);
		}
		catch (Exception ex){
			System.out.println("Main program caught exception");
		}
		System.out.println("\n demoTheAfterThrowingAdvice");
		System.out.println(accounts);
	}

	private void demoTheAfterReturningAdvice(AccountDao accountDao) {
		List<Account> accounts = accountDao.findAccounts();
		System.out.println("\n demoTheAfterReturningAdvice");
		System.out.println(accounts);
	}

	private void demoTheBeforeAdvice(AccountDao accountDao, MembershipDao membershipDao) {
		Account account = new Account();
		accountDao.addAccount(account, true);
		membershipDao.addAccount();
	}


}
