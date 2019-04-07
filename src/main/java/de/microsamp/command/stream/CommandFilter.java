package de.microsamp.command.stream;

public interface CommandFilter extends CommandProcessor {

	boolean validate(String... args);

}
