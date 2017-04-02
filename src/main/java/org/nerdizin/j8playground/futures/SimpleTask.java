package org.nerdizin.j8playground.futures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTask implements Runnable {

	private final static Logger LOG = LoggerFactory.getLogger(SimpleTask.class);
	
	@Override
	public void run() {
		LOG.info("Done");
	}

}
