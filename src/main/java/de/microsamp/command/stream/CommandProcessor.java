package de.microsamp.command.stream;

public interface CommandProcessor {

	default String[] process(String... args) {
		this.handle(args);
		return args;
	}

	default void handle(String... args) { }

	default boolean validate(String... args) {
		return true;
	}

}
