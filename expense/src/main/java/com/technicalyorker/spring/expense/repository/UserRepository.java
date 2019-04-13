package com.technicalyorker.spring.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import static com.technicalyorker.spring.expense.constants.Constants.ROLE_ADMIN;
import org.springframework.data.jpa.repository.Query;

import com.technicalyorker.spring.expense.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Long deleteUserById(Long id);

	public User findUserByName(String userName);

	public int countByName(String userName);

	@Query("Select u from User u  where '" + ROLE_ADMIN + "'=(select role from User u2 where u2.name=?1) or u.name=?1")
	public Iterable<User> findAll(String me);
}
