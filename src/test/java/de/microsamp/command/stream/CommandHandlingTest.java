package de.microsamp.command.stream;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class CommandHandlingTest {

	private static final String INPUT = "label firstArg secondArg thirdArg";

	@Test
	public void testCommandHandling() {
		AtomicInteger counter = new AtomicInteger();

		CommandStream stream = new CommandStream()
			.handle((args) -> {
				assertEquals(0, counter.get());
				assertEquals("firstArg", args[0]);
				counter.incrementAndGet();
			})
			.handle((args) -> {
				assertEquals(1, counter.get());
				assertEquals("secondArg", args[1]);
				counter.incrementAndGet();
			});

		stream.process(INPUT);

		assertEquals(2, counter.get());
	}

}
