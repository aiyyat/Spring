package com.technicalyorker.spring.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.technicalyorker.spring.expense.domain.Category;
import com.technicalyorker.spring.expense.repository.CategoryRepository;

@Service
@Repository
public class CategoryService {
	@Autowired
	CategoryRepository cRepo;

	public void add(Category c) {
		cRepo.save(c);
	}

	public void save(Category c) {
		cRepo.save(c);
	}

	public Category root() {
		return cRepo.findCategoryByName("Root");
	}

	public void removeAll() {
		cRepo.deleteAll();
	}

	public List<Category> allParents() {
		return cRepo.findCategorysByParent(root());
	}

	public void delete(Category category) {
		cRepo.delete(category);
	}

	public Category findById(Long id) {
		return cRepo.findOne(id);
	}

	public Category refresh(Category cat) {
		return cRepo.findOne(cat.getId());
	}
}
