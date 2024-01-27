package com.example.splitwise.models;

import java.util.List;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends BaseModel{
	private int totalAmount;
	
	@Enumerated(EnumType.ORDINAL)
	private ExpenseType expensetype;
	
	@OneToMany
	private List<UserExpense> userExpenses;
	
	@ManyToOne
	private Group group;

}
