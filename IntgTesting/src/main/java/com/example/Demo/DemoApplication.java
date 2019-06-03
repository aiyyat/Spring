package com.example.Demo;

import org.aspectj.apache.bcel.util.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    StudentRepository studentRepository;

    @Bean
    public CommandLineRunner init() {
        return a -> {
            Stream.of("Josh", "Long", "Thomas").forEach(e -> {
                studentRepository.save(new Student(e));
            });
        };
    }
}

@RestController
@RequestMapping("/")
class Endpoints {
    @Autowired
    StudentRepository studentRepo;

    @RequestMapping(value = "/students", produces = "application/json")
    public ResponseEntity<List<Student>> allStudents() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("version", "v1.2");
        return new ResponseEntity<>(studentRepo.findAll(), headers, HttpStatus.OK);
    }
}

interface StudentRepository extends JpaRepository<Student, Integer> {

}

