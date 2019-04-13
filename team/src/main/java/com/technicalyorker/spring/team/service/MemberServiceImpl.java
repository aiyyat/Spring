package com.technicalyorker.spring.team.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technicalyorker.spring.team.model.Member;
import com.technicalyorker.spring.team.repository.MemberRepository;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberRepository memberRepository;

	public List<Member> findAllMembers() {
		return memberRepository.findAll();
	}

	public Member findById(Long id) {
		return memberRepository.findOne(id);
	}

	public Member findByName(String name) {
		return memberRepository.findByName(name);
	}

	public void saveMember(Member member) {
		memberRepository.save(member);
	}

	public void updateMember(Member member) {
		memberRepository.save(member);
	}

	public void deleteMemberById(Long id) {
		memberRepository.delete(id);
	}

	public boolean doesMemberExist(Member member) {
		try {
			if (null != findByName(member.getName())) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public void deleteAllMembers() {
		memberRepository.deleteAll();
	}
}