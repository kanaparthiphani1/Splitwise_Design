package com.example.splitwise.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class CommandExecutor {
	 List<Commands> commands;

	    @Autowired
	    CommandExecutor(SettleUpCommand settleUpUserCommand) {
	        commands.add(settleUpUserCommand);
	    }
	    public void addCommand(){
	    }

	    public void removeCommand(){
	    }

	    public void execute(String input){
	        for(Commands command : commands){
	            if(command.matches(input)){
	                command.execute(input);
	                break;
	            }
	        }
	        throw new RuntimeException("Command not found");
	    }
}
