package org.nerdizin.j8playground.futures;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongRunningTaskWithResult implements Supplier<String> {
	
	private final static Logger LOG = LoggerFactory.getLogger(LongRunningTaskWithResult.class);
	
	@Override
	public String get() {
		
		LOG.info("Task started");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("Task ended");
		
		return "Some result";
	}
}
