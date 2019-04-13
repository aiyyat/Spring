package com.technicalyorker.spring.store.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.technicalyorker.spring.store.domain.Store;

public interface StoreRepository extends MongoRepository<Store, String> {
	public Store findByStoreName(String storeName);

	public void deleteStoreByStoreName(String storeName);
}
