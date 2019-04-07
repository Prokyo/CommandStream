package de.microsamp.command.stream;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

public class CommandSubStreamTest {

	@Test
	public void testSubStream() {
		AtomicBoolean success = new AtomicBoolean();

		CommandStream stream = new CommandStream()
			.sub(
				args -> args[0].equalsIgnoreCase("firstBranch"),
				sub -> sub.handle(args -> success.set(true))
			)
			.sub(
				args -> args[0].equalsIgnoreCase("secondBranch"),
				sub -> sub.handle(args -> fail())
			);

		stream.process("cmd firstBranch");

		assertTrue(success.get());
	}

}
