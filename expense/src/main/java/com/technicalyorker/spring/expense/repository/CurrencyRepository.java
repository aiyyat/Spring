package com.technicalyorker.spring.expense.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicalyorker.spring.expense.domain.Currency;

@Transactional
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
	public Currency findByCode(String code);
}
