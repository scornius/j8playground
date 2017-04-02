package org.nerdizin.j8playground.futures;

import java.util.function.Supplier;

public class TaskWithException implements Supplier<String> {

	@Override
	public String get() {
		throw new RuntimeException("Oops");
	}

}
