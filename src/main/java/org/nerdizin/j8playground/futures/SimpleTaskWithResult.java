package org.nerdizin.j8playground.futures;

import java.util.function.Supplier;

public class SimpleTaskWithResult implements Supplier<String> {

	@Override
	public String get() {
		return "Some Result";
	}

}
