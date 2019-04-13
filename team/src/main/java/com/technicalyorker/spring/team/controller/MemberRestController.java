package com.technicalyorker.spring.team.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.technicalyorker.spring.team.model.Member;
import com.technicalyorker.spring.team.service.MemberService;

@RestController
public class MemberRestController {

	@Autowired
	MemberService memberService;
	private final Logger logger = LoggerFactory.getLogger(MemberRestController.class);

	@RequestMapping(value = "/member/", method = RequestMethod.GET)
	public ResponseEntity<List<Member>> listAllMembers() {
		List<Member> members = memberService.findAllMembers();
		if (members.isEmpty()) {
			return new ResponseEntity<List<Member>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
	}

	@RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Member> getMember(@PathVariable("id") long id) {
		Member member = memberService.findById(id);
		if (member == null) {
			return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}

	@RequestMapping(value = "/member/", method = RequestMethod.POST)
	public ResponseEntity<Void> createMember(@RequestBody Member member, UriComponentsBuilder ucBuilder) {
		if (memberService.doesMemberExist(member)) {
			System.out.println("A Member with name " + member.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		memberService.saveMember(member);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/member/{id}").buildAndExpand(member.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/member/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Member> updateMember(@PathVariable("id") long id, @RequestBody Member member) {
		Member currentMember = memberService.findById(id);
		if (currentMember == null) {
			System.out.println("Member with id " + id + " not found");
			return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
		}
		currentMember.setName(member.getName());
		currentMember.setRole(member.getRole());
		currentMember.setEmail(member.getEmail());
		memberService.updateMember(currentMember);
		return new ResponseEntity<Member>(currentMember, HttpStatus.OK);
	}

	@RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Member> deleteMember(@PathVariable("id") long id) {
		Member member = memberService.findById(id);
		if (member == null) {
			return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
		}

		memberService.deleteMemberById(id);
		return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/member/", method = RequestMethod.DELETE)
	public ResponseEntity<Member> deleteAllMembers() {
		memberService.deleteAllMembers();
		return new ResponseEntity<Member>(HttpStatus.NO_CONTENT);
	}

}