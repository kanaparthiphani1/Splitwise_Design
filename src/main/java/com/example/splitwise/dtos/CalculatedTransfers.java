package com.example.splitwise.dtos;

import com.example.splitwise.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedTransfers {
	
	private User from;
	private User to;
	private Double amount;

}
