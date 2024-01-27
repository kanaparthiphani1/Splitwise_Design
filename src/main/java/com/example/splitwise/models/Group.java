package com.example.splitwise.models;

import java.util.List;

import jakarta.persistence.ManyToMany;
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
public class Group {
	private String name;
	@ManyToOne
	private User admin;
	@OneToMany
	private List<Expense> expenses;
	@ManyToMany
	private List<User> users;
}
