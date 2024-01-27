package com.example.splitwise.commands;

public interface Commands {
	 public boolean matches(String input);
	 public void execute(String input);

}
