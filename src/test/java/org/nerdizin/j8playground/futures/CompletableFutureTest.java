package org.nerdizin.j8playground.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompletableFutureTest {

	private final static Logger LOG = LoggerFactory.getLogger(CompletableFutureTest.class);
	
	private final SimpleTask simpleTask = new SimpleTask();
	private final SimpleTaskWithResult simpleTaskWithResult = new SimpleTaskWithResult();
	private final LongRunningTask longRunningTask = new LongRunningTask();
	private final LongRunningTaskWithResult longRunningTaskWithResult = new LongRunningTaskWithResult();
	private final TaskWithException taskWithException = new TaskWithException();
	
	@Test
	public void testRunAsyncGet() {
		
		CompletableFuture<Void> future = CompletableFuture.runAsync(longRunningTask);
		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		LOG.info("complete");
	}
	
	@Test
	public void testSupplyAsyncGet() {
		
		CompletableFuture<String> future = CompletableFuture.supplyAsync(longRunningTaskWithResult);
		try {
			LOG.info("result:" + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		LOG.info("complete");
	}
	
	@Test
	public void testThenApply() throws Exception {
		
		CompletableFuture<String> future = CompletableFuture
				.supplyAsync(simpleTaskWithResult)
				.thenApply(s->s=s.replace("S", "s"))
				.thenApplyAsync(s->s=s.replace("o", "O"));
		
		LOG.info("result:" + future.get());
	}
	
	@Test
	public void testExceptionally() throws Exception {
		
		CompletableFuture<String> future = CompletableFuture
				.supplyAsync(taskWithException)
				.thenApplyAsync(s->s="Some other result")
				.exceptionally(e->"Error: " + e)
				.thenApplyAsync(s->s=s.replace("o", "O"));
		
		LOG.info("result:" + future.get());
	}
	
	@Test
	public void testHandle() throws Exception {
		
		CompletableFuture<String> future = CompletableFuture
				.supplyAsync(taskWithException)
				.handleAsync((s,e)->{
					if (s==null) {
						LOG.warn("s is null", e);
						return "A default value";
					}
					return s;
				})
				.thenApplyAsync(s->s=s.replace("u", "U"));
		
		LOG.info("result:" + future.get());
	}
	
	@Test
	public void testThenAcceptAndRun() throws Exception {
		
		CompletableFuture<Void> future = CompletableFuture
				.supplyAsync(simpleTaskWithResult)
				.thenAcceptAsync(s->LOG.info("s=" + s))
				.thenRunAsync(()->LOG.info("done"));
		
		LOG.info("result:" + future.get());
	}
	
	
	
	 
}
