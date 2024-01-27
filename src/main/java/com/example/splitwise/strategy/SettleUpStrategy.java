package com.example.splitwise.strategy;

import java.util.List;

import com.example.splitwise.models.Expense;

public interface SettleUpStrategy {
	 public List<Expense> settleUp(List<Expense> expensesToSettleUp);
}
