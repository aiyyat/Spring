package com.technicalyorker.spring.team.service;

import java.util.List;

import com.technicalyorker.spring.team.model.Member;

public interface MemberService {
	Member findById(Long id);

	Member findByName(String name);

	void saveMember(Member member);

	void updateMember(Member member);

	void deleteMemberById(Long id);

	List<Member> findAllMembers();

	void deleteAllMembers();

	public boolean doesMemberExist(Member member);
}
