package pl.markon.ideas.handlers;

import pl.markon.ideas.input.UserInputCommand;

public class HelpCommandHendler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "help";

    @Override
    public void handle(UserInputCommand command) {
        System.out.println("Help");
        System.out.println("Allowed cammands: help, quit, category, question, answer");
        System.out.println("Command pattern: <command> <action> <param1> <param2>");
        System.out.println("Example: category add CategoryName");
    }
    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
