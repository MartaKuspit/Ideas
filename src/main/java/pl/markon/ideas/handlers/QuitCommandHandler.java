package pl.markon.ideas.handlers;

import pl.markon.ideas.QuitIdeasApplicationException;
import pl.markon.ideas.input.UserInputCommand;

public class QuitCommandHandler extends BaseCommandHandler {
    public static final String COMMAND_NAME = "quit";

    @Override
    public void handle(UserInputCommand command) {
       throw new QuitIdeasApplicationException();
    }


    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
