package de.microsamp.command.stream;

import org.junit.Assert;
import org.junit.Test;

public class CommandFilteringTest {

	@Test
	public void testCommandFiltering() {
		CommandStream stream = new CommandStream()
			.filter(args -> isInteger(args[0], 10))
			.handle(args -> Assert.fail());

		stream.process("cmd 133seven");
	}

	private static boolean isInteger(String s, int radix) {
		if(s.isEmpty()) return false;
		for(int i = 0; i < s.length(); i++) {
			if(i == 0 && s.charAt(i) == '-') {
				if(s.length() == 1) return false;
				else continue;
			}
			if(Character.digit(s.charAt(i),radix) < 0) return false;
		}
		return true;
	}

}
