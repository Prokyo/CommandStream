package de.microsamp.command.stream;

public abstract class ArgumentHandler <T> implements CommandProcessor {

	private final int index;

	public ArgumentHandler(int index) {
		this.index = index;
	}

	@Override
	public void handle(String... args) {
		this.handleArg((T) args[index]);
	}

	abstract void handleArg(T argument);

}
