package com.alishevich.usermanager;

import com.alishevich.usermanager.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@AllArgsConstructor
public class UserManagerApplication implements ApplicationRunner {

	private final UserAccountRepository userAccountRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		System.out.println(userAccountRepository.findAll());
	}

}
