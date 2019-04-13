package com.technicalyorker.spring.tdd;

import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.technicalyorker.spring.store.domain.Store;
import com.technicalyorker.spring.store.repository.StoreRepository;
import com.technicalyorker.spring.store.service.StoreService;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { StoreService.class, StoreRepository.class })
@EnableMongoRepositories
@ImportResource("file:**/mongo-config.xml")
public class StoreApplicationTests {
	private static final String CUPERTINO_STORE = "Cupertino Store";
	@Autowired
	private StoreService storeService;
	@Autowired
	private StoreRepository storeDetailRepository;

	@Before
	public void init() {
		storeDetailRepository.save(new Store(CUPERTINO_STORE, "California, US", true));
		storeDetailRepository.save(new Store("Singapore  Store", "Ang Mio Kio SG", false));
	}

	@After
	public void destroy() {
		storeDetailRepository.deleteAll();
	}

	@Test
	public void testRepositoryAllStoreDetails() {
		System.out.println(storeDetailRepository.findAll());
		TestCase.assertEquals(storeDetailRepository.findAll().stream().filter(r -> r.getStoreId() != null)
				.collect(Collectors.toList()).size(), 2);
	}

	@Test
	public void testRepository() {
		TestCase.assertNotNull(storeDetailRepository.findByStoreName(CUPERTINO_STORE).getStoreId());
	}

	@Test
	public void testService() {
		TestCase.assertEquals(storeService.allDisplayableStores().size(), 1);
	}

}
