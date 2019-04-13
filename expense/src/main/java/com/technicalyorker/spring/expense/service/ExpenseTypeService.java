package com.technicalyorker.spring.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.technicalyorker.spring.expense.domain.ExpenseType;
import com.technicalyorker.spring.expense.repository.ExpenseTypeRepository;

@Service
@Repository
public class ExpenseTypeService {
	@Autowired
	ExpenseTypeRepository cRepo;

	public void add(ExpenseType c) {
		cRepo.save(c);
	}

	public void save(ExpenseType c) {
		cRepo.save(c);
	}

	public void removeAll() {
		cRepo.deleteAll();
	}

	public void delete(ExpenseType ExpenseType) {
		cRepo.delete(ExpenseType);
	}

	public ExpenseType findById(Long id) {
		return cRepo.findOne(id);
	}

	public List<ExpenseType> all() {
		return cRepo.findAll();
	}
}
