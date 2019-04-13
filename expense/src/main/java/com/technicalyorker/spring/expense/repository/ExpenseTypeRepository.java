package com.technicalyorker.spring.expense.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicalyorker.spring.expense.domain.ExpenseType;

@Transactional
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
}
