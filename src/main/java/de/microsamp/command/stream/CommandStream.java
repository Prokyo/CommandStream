package de.microsamp.command.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CommandStream implements CommandHandler {

	private final List<CommandProcessor> processors = new ArrayList<>();
	private Function<String[], Boolean> argsCheck; // checks whether the arguments are meant for this sub stream or not, null if this is the parent

	public CommandStream() {
	}

	protected CommandStream(Function<String[], Boolean> argsCheck) {
		this.argsCheck = argsCheck;
	}

	@Deprecated
	@Override
	public void handle(String... args) {
		if(this.argsCheck != null && !this.argsCheck.apply(args)) return;

		for(int i = 0; i < this.processors.size(); i++) {
			CommandProcessor processor = this.processors.get(i);
			if(!processor.validate(args)) break;
			args = processor.process(args);
		}
	}

	public void process(String input) {
		this.process(input, " ");
	}

	public void process(String input, String delimiter) {
		String[] args = input.split(delimiter);
		args = Arrays.copyOfRange(args, 1, args.length);
		this.handle(args);
	}

	public CommandStream handle(CommandHandler handler) {
		return this.processor(handler);
	}

	public CommandStream filter(CommandFilter filter) {
		return this.processor(filter);
	}

	public CommandStream processor(CommandProcessor processor) {
		this.processors.add(processor);
		return this;
	}

	public <T> CommandStream handleArg(int index, Consumer<T> consumer) {
		this.processors.add(new ArgumentHandler<T>(index) {
			@Override
			void handleArg(T argument) {
				consumer.accept(argument);
			}
		});
		return this;
	}

	public CommandStream sub(Function<String[], Boolean> check, Consumer<CommandStream> subStreamConsumer) {
		CommandStream subStream = new CommandStream(check);
		subStreamConsumer.accept(subStream);
		this.handle(subStream);
		return this;
	}

}
