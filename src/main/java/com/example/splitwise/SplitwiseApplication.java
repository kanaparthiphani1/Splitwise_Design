package com.example.splitwise;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.splitwise.commands.CommandExecutor;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

	private CommandExecutor commandExecutor;
	private Scanner sc = new Scanner(System.in);

	@Autowired
	SplitwiseApplication(CommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		while (true) {
			String input = sc.next();
			commandExecutor.execute(input);
		}
	}

}
