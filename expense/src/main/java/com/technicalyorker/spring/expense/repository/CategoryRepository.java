package com.technicalyorker.spring.expense.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicalyorker.spring.expense.domain.Category;

@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findCategoryByName(String name);

	List<Category> findCategorysByParent(Category cat);
}
