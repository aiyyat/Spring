package com.technicalyorker.spring.store;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.technicalyorker.spring.store.domain.Store;
import com.technicalyorker.spring.store.repository.StoreRepository;

@SpringBootApplication
public class StoreApplication {
	@Autowired
	StoreRepository sr;
	Random r = new Random();

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				Arrays.stream(getStores()).forEach(s -> {
					sr.save(s);
					System.out.println("Saved:" + s);
				});
			}

			private Store[] getStores() {
				Store[] s = new Store[3];
				for (int i = 0; i < s.length; i++) {
					s[i] = new Store(getStoreName(), getStoreAddress());
				}
				return s;
			}

			private String getStoreAddress() {
				return "Infinite Loop, California, US";
			}

			private String getStoreName() {
				return "StoreName:" + r.nextInt(10000);
			}
		};
	}
}
