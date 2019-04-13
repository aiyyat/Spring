package com.technicalyorker.spring.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.technicalyorker.spring.expense.domain.Currency;
import com.technicalyorker.spring.expense.repository.CurrencyRepository;

@Service
@Repository
public class CurrencyService {
	@Autowired
	CurrencyRepository cRepo;

	public void add(Currency c) {
		cRepo.save(c);
	}

	public void save(Currency c) {
		cRepo.save(c);
	}

	public void delete(Currency currency) {
		cRepo.delete(currency);
	}

	public Currency findByCode(String code) {
		return cRepo.findByCode(code);
	}

	public List<Currency> all() {
		return cRepo.findAll();
	}
}
