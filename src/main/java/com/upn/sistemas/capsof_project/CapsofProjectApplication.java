package com.upn.sistemas.capsof_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CapsofProjectApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CapsofProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String pass = "1234";

		for (int i = 0; i < 4; i++) {
			String passEncode = passwordEncoder.encode(pass);
		}
	}

}
