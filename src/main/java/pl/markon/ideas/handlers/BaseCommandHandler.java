package pl.markon.ideas.handlers;

import pl.markon.ideas.input.UserInputCommand;

abstract class BaseCommandHandler implements CommandHandler {

    @Override
    public boolean supports(String name) {
        return getCommandName().equals(name);
    }

    protected abstract String getCommandName();
}
