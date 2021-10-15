package pl.markon.ideas;

import pl.markon.ideas.handlers.*;
import pl.markon.ideas.input.UserInputCommand;
import pl.markon.ideas.input.UserInputManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IdeasApplication {

    private static Logger LOG = Logger.getLogger(IdeasApplication.class.getName());

    public static void main(String[] args) {
        new IdeasApplication().start();
    }

    private void start() {
        //System.out.println("Satrt app ...");
        LOG.info("Start app ...");

        boolean applicationLoop = true;
        UserInputManager userInputManager = new UserInputManager();

        List<CommandHandler> handlers = new ArrayList<>();
        handlers.add(new HelpCommandHendler()); //help
        handlers.add(new QuitCommandHandler()); //quit
        handlers.add(new CategoryCommandHandler());
        handlers.add(new QuestionCommandHandler());
        handlers.add(new AnswerCommandHandler());

        while (applicationLoop) {
            try {
                UserInputCommand userInputCommand = userInputManager.nextCommand();
                //System.out.println(userInputCommand);
                LOG.info(userInputCommand.toString());

                Optional<CommandHandler> currentHandler = Optional.empty();
                for (CommandHandler handler : handlers) {
                    if (handler.supports(userInputCommand.getCommand())) {
                        currentHandler = Optional.of(handler);
                        break;
                    }
                }
                currentHandler
                        .orElseThrow(() -> new IllegalArgumentException("Unknown handler: " + userInputCommand.getCommand()))
                        .handle(userInputCommand);
            }catch (QuitIdeasApplicationException e) {
                //System.out.println("Quit...");
                LOG.info("Quit...");
                applicationLoop = false;
            }catch (IllegalArgumentException e){
                LOG.log(Level.WARNING,"Validation eception: " + e.getMessage());

            } catch (Exception e) {
               // e.printStackTrace();
                LOG.log(Level.SEVERE,"Unknown error",e);
            }

        }
    }
}
