package net.mohamed.customer_service;

import net.mohamed.customer_service.config.GlobalConfig;
import net.mohamed.customer_service.entities.Customer;
import net.mohamed.customer_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			List<Customer> customerList= List.of(
				Customer.builder()
							.firstName("mohamed")
							.email("aghnaoumohamed@gmail.com")
							.lastName("aghnaou")
							.build(),
		        Customer.builder()
					.firstName("ali")
					.email("aghnaouali@gmail.com")
					.lastName("aghnaou")
					.build()
			);
			customerRepository.saveAll(customerList);
		};
	}

}
