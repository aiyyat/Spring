package com.technicalyorker.spring;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
	private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

	@Test
	public void testLogger() {
		logger.debug("info");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
}
