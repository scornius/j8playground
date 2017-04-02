package org.nerdizin.j8playground.futures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongRunningTask implements Runnable {

	private final static Logger LOG = LoggerFactory.getLogger(LongRunningTask.class);
	
	public void run() {
		LOG.info("Task started");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("Task ended");
	}
}
