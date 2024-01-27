package com.example.splitwise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.splitwise.models.User;
import com.example.splitwise.models.UserExpense;

@Repository
public interface UserExpenseRepo extends JpaRepository<UserExpense, Long>{
	@Query
	List<UserExpense> findByUser(User user);
}
