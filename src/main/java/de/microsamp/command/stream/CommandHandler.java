package de.microsamp.command.stream;

public interface CommandHandler extends CommandProcessor {

	void handle(String... args);

}
