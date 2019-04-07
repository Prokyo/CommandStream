package de.microsamp.command.stream;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class CommandArgumentTest {

	private static final String INPUT = "label firstArg secondArg thirdArg";

	@Test
	public void testCommandHandling() {
		AtomicInteger counter = new AtomicInteger();

		CommandStream stream = new CommandStream()
			.handleArg(0, (arg) -> {
				assertEquals(0, counter.get());
				assertEquals("firstArg", arg);
				counter.incrementAndGet();
			})
			.handleArg(1, (arg) -> {
				assertEquals(1, counter.get());
				assertEquals("secondArg", arg);
				counter.incrementAndGet();
			});

		stream.process(INPUT);

		assertEquals(2, counter.get());
	}

}
