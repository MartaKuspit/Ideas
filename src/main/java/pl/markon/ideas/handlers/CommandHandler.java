package pl.markon.ideas.handlers;

import pl.markon.ideas.input.UserInputCommand;

public interface CommandHandler {
    void handle(UserInputCommand command);
    boolean supports(String name);
}
