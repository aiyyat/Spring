package com.technicalyorker.spring.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicalyorker.spring.team.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public Member findByName(String name);
}
