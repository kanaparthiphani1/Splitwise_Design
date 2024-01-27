package com.example.splitwise.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.User;
import com.example.splitwise.models.UserExpense;
import com.example.splitwise.repositories.UserExpenseRepo;
import com.example.splitwise.repositories.UserRepository;
import com.example.splitwise.strategy.SettleUpStrategy;

@Service
public class SettleupService {
	
	UserRepository userRepo;
	UserExpenseRepo userExpRepo;
	SettleUpStrategy settleUpStrategy;
	
	@Autowired
	public SettleupService(UserRepository userRepo, UserExpenseRepo userExpRepo, SettleUpStrategy settleUpStrategy) {
		super();
		this.userRepo = userRepo;
		this.userExpRepo = userExpRepo;
		this.settleUpStrategy = settleUpStrategy;
	}




	public List<Expense> settleUpUser(Long userId){
		// 1. getting all the expenses related to the user
        // 2. Iterate through all the expenses to check who owns what
        // 3. generate transactions to settle up
        // 4. return transactions
		
		Optional<User> userOpt =  userRepo.findById(userId);
		List<UserExpense> userExpenses = userExpRepo.findByUser(userOpt.get());
		Set<Expense> expenses = new HashSet<>();
		
		for(UserExpense userExpense : userExpenses) {
			expenses.add(userExpense.getExpense());
		}
		
		List<Expense> transactions = settleUpStrategy.settleUp(expenses.stream().toList());
		
		//filter;
		
		return transactions; 
	}
}
