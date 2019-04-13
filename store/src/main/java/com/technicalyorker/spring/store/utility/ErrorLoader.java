package com.technicalyorker.spring.store.utility;

import static com.technicalyorker.spring.store.constant.StoreConstants.DESCRIPTION;
import static com.technicalyorker.spring.store.constant.StoreConstants.DOT;
import static com.technicalyorker.spring.store.constant.StoreConstants.STATUS;
import static com.technicalyorker.spring.store.constant.StoreConstants.ERROR;
import static com.technicalyorker.spring.store.constant.StoreConstants.MODULE;
import static com.technicalyorker.spring.store.constant.StoreConstants.RESPONSE;
import static com.technicalyorker.spring.store.constant.StoreConstants.SEVERITY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.technicalyorker.spring.store.config.StoreError;

@Service
@PropertySource("classpath:errors.properties")
public class ErrorLoader {
	@Autowired
	Environment env;

	public StoreError getStoreError(String code) {
		StoreError se = new StoreError();
		se.setCode(code);
		final String prefix = ERROR + DOT + code + DOT;
		se.setDescription(env.getProperty(prefix + DESCRIPTION));
		se.setModule(env.getProperty(prefix + MODULE));
		se.setSeverity(env.getProperty(prefix + SEVERITY));
		se.setStatus(env.getProperty(prefix + RESPONSE + DOT + STATUS));
		return se;
	}
}
