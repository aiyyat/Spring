package com.technicalyorker.spring.store.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.technicalyorker.spring.store.exception.InternalServerStoreException;
import com.technicalyorker.spring.store.exception.StoreException;

@Aspect
@Component
public class ExceptionLoggerInterceptor {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterThrowing(pointcut = "execution(* com.technicalyorker.spring.store.endpoint..*(..))", throwing = "e")
	public void methodCall(Exception e) {
		if (!(e instanceof StoreException)) {
			logger.error(e.getMessage(), e);
			throw new InternalServerStoreException();
		} else {
			logger.info("Known Error Occurs:" + e.getMessage());
		}
	}
}
