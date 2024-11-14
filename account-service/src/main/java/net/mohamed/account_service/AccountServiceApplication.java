package net.mohamed.account_service;

import net.mohamed.account_service.clients.CustomerRestClient;
import net.mohamed.account_service.entities.BankAccount;
import net.mohamed.account_service.enums.AccountType;
import net.mohamed.account_service.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
		return args -> {

			customerRestClient.AllCustomers().forEach(customer -> {
				List<BankAccount> accountList=List.of(
						BankAccount.builder()
								.accountId(UUID.randomUUID().toString())
								.currency("MAD")
								.balance(Math.random()*95000)
								.createdAt(LocalDate.now())
								.type(AccountType.CURRENT_ACCOUNT)
								.customerId(Long.valueOf(customer.getId()))
								.build(),
						BankAccount.builder()
						         .accountId(UUID.randomUUID().toString())
						         .currency("MAD")
						         .balance(Math.random()*50000)
						         .createdAt(LocalDate.now())
						         .type(AccountType.SAVING_ACCOUNT)
						         .customerId(Long.valueOf(customer.getId()))
						         .build()
				);
				bankAccountRepository.saveAll(accountList);

			});


		};
	}

}
