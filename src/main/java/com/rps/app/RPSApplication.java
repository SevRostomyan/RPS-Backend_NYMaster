package com.rps.app;

import com.rps.app.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RPSApplication implements CommandLineRunner {
	@Autowired
	PlayerRepository playerRepository;
	public static void main(String[] args) {
		SpringApplication.run(RPSApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
