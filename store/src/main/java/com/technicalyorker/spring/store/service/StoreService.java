package com.technicalyorker.spring.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicalyorker.spring.store.domain.Store;
import com.technicalyorker.spring.store.repository.StoreRepository;

@Service
public class StoreService {
	@Autowired
	private StoreRepository sr;

	public List<Store> allStores() {
		return sr.findAll();
	}

	public List<Store> allDisplayableStores() {
		return sr.findAll().stream().filter(r -> r.isDisplay()).collect(Collectors.toList());
	}

	public void createStore(String storeName, String storeAddress) {
		sr.save(new Store(storeName, storeAddress));
	}

	public void deleteStoreByName(String storeName) {
		sr.deleteStoreByStoreName(storeName);
	}

	public void deleteAll() {
		sr.deleteAll();
	}
}
