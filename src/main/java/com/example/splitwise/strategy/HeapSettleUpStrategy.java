package com.example.splitwise.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseType;
import com.example.splitwise.models.User;
import com.example.splitwise.models.UserExpense;
import com.example.splitwise.models.UserExpensetype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Transaction{
	private User user;
	private Double amount;
}

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy{

	@Override
	public List<Expense> settleUp(List<Expense> expensesToSettleUp) {
		// TODO Auto-generated method stub
		// create PriorityQueues
        // Going through all expenses
            // go through all expenseUser
                // finalExpense[user] += amount

        // finalExpense[user] => either of the PQs

        // while (!pq are not empty)
            // max from PQ 1 = Paid : A
            // max from PQ 2 = who had to pay : B
            // B -> A : min(max1 , max2)
		
		Queue<Transaction> paid = new PriorityQueue<>((a, b) -> (int)(b.getAmount() - a.getAmount()));
		Queue<Transaction> haveToPay = new PriorityQueue<>();
		
		List<Expense> expenseList = new ArrayList<>();
		
		Map<User,Double> cummulateMap = new HashMap<>();
		for(Expense ex:expensesToSettleUp){
			for(UserExpense userEx : ex.getUserExpenses()) {
				if(cummulateMap.containsKey(userEx.getUser())) {
					if(userEx.getUserExpenseType().equals(UserExpensetype.PAID_BY)) {
						cummulateMap.replace(userEx.getUser(), cummulateMap.get(userEx.getUser())+userEx.getAmount()); 
					}else {
						cummulateMap.replace(userEx.getUser(), cummulateMap.get(userEx.getUser())-userEx.getAmount()); 
					}
				}else {
					if(userEx.getUserExpenseType().equals(UserExpensetype.PAID_BY)) {
						cummulateMap.put(userEx.getUser(), (double) userEx.getAmount());
					}else {
						cummulateMap.put(userEx.getUser(), (double) userEx.getAmount()*-1);
					}
				}
				
			}
			
		}
		
		for(Map.Entry<User, Double> entry:cummulateMap.entrySet()) {
			if(entry.getValue()>0) {
				paid.add(new Transaction(entry.getKey(),entry.getValue()));
			}else {
				haveToPay.add(new Transaction(entry.getKey(),entry.getValue()));
			}
		}
		
		while(!paid.isEmpty() && !haveToPay.isEmpty()) {
			Double maxPaidVal = paid.peek().getAmount();
			Double maxHavetoPay = haveToPay.peek().getAmount();
			
			if(maxPaidVal>maxHavetoPay) {
				Transaction haveToPayObj = haveToPay.poll();
				List<UserExpense> userE = new ArrayList<>();
				userE.add(new UserExpense(haveToPayObj.getUser(),maxHavetoPay.intValue(),null,UserExpensetype.PAID_BY));
				userE.add(new UserExpense(paid.peek().getUser(),maxHavetoPay.intValue(),null,UserExpensetype.HAD_TO_PAY));
				expenseList.add(new Expense(maxHavetoPay.intValue(), ExpenseType.TRANSACTION, userE, null));
				paid.peek().setAmount(paid.peek().getAmount()-maxHavetoPay);
			}else {
				Transaction paidObj = paid.poll();
				List<UserExpense> userE = new ArrayList<>();
				userE.add(new UserExpense(haveToPay.peek().getUser(),maxPaidVal.intValue(),null,UserExpensetype.PAID_BY));
				userE.add(new UserExpense(paidObj.getUser(),maxPaidVal.intValue(),null,UserExpensetype.HAD_TO_PAY));
				expenseList.add(new Expense(maxPaidVal.intValue(), ExpenseType.TRANSACTION, userE, null));
				haveToPay.peek().setAmount(haveToPay.peek().getAmount()-maxPaidVal);
			}
			
		}
		
		return expenseList;
	}
	
}
