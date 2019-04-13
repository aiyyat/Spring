package com.technicalyorker.spring.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technicalyorker.spring.expense.domain.User;
import com.technicalyorker.spring.expense.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository uRepo;

	public void addUser(User e) {
		uRepo.save(e);
	}

	public Iterable<User> all(String me) {
		return uRepo.findAll(me);
	}

	public void deleteById(Long id, String me) {
		System.out.println("Deleted" + uRepo.deleteUserById(id));
	}

	public User getUser(String userName) {
		return uRepo.findUserByName(userName);
	}

	public User getUserById(Long id) {
		return uRepo.findOne(id);
	}

	public void removeAll() {
		uRepo.deleteAll();
	}
}
