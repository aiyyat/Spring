package com.technicalyorker.spring.team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.technicalyorker.spring.team.controller.MemberRestController;
import com.technicalyorker.spring.team.model.Member;
import com.technicalyorker.spring.team.service.MemberService;

@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application {
	@Autowired
	private MemberService us;
	private final Logger logger = LoggerFactory.getLogger(MemberRestController.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner loadSampleData() {
		return (args) -> {
			getMemberList().stream().filter((u) -> null == u.getId()).forEach((u) -> us.saveMember(u));
			logger.debug("Members Persisted:" + us.findAllMembers().stream().map(m -> m.getId() + ":" + m.getName())
					.collect(Collectors.joining(", ")));
		};
	}

	private List<Member> getMemberList() {
		List<Member> memberList = new ArrayList<>();
		memberList.add(new Member("Raja V", "Senior Project Manager", "vraja@ncs.com.sg"));
		memberList.add(new Member("Jayakumar", "Technical Manager", "tjaya@ncs.com.sg"));
		memberList.add(new Member("Ramki R", "Programmer", "ramkir@ncs.com.sg"));
		memberList.add(new Member("Saravana M", "Programmer", "msara@ncs.com.sg"));
		memberList.add(new Member("Prateek A", "Programmer", "prateeka@ncs.com.sg"));
		return memberList;
	}
}