package com.example.splitwise.dtos;

import java.util.List;

import com.example.splitwise.models.Expense;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SettleUpUserResponseDTO {
	private ResponseType responseType;
    private List<Expense> expenses;
}
