package com.example.splitwise.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserExpense {
	@ManyToOne
	private User user;
	private int amount;
	
	@ManyToOne
	private Expense expense;
	
    @Enumerated(EnumType.ORDINAL)
	private UserExpensetype userExpenseType;
}
