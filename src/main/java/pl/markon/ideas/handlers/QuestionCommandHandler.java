package pl.markon.ideas.handlers;

import pl.markon.ideas.dao.CategoryDao;
import pl.markon.ideas.dao.QuestionDao;
import pl.markon.ideas.input.UserInputCommand;
import pl.markon.ideas.model.Category;
import pl.markon.ideas.model.Question;

import java.util.List;
import java.util.logging.Logger;

public class QuestionCommandHandler extends BaseCommandHandler {
    private static Logger LOG = Logger.getLogger(QuestionCommandHandler.class.getName());
    public static final String COMMAND_NAME = "question";

    private QuestionDao questionDao;
    private CategoryDao categoryDao;

    public QuestionCommandHandler() {
        questionDao = new QuestionDao();
        categoryDao = new CategoryDao();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void handle(UserInputCommand command) {
        if (command.getAction() == null) {
            throw new IllegalArgumentException("action can't be null");
        }
        switch (command.getAction()) {
            case LIST:
                //System.out.println("List of questions");
                LOG.info("List of questions...");
                if (!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("questions' list does't support any additional params");
                }
                List<Question> questions = questionDao.findAll();
                questions.forEach(System.out::println);
                break;
            case ADD:
                //System.out.println("Add question ..");
                LOG.info("Add question...");
                //TODO validate params
                if (command.getParam().size() != 2) {
                    throw new IllegalArgumentException("wrong command format, check help for more information");
                }
                String categoryName = command.getParam().get(0);
                String questionName = command.getParam().get(1);
                Category category = categoryDao.findOne(categoryName)
                        .orElseThrow(() -> new IllegalArgumentException("Category not found " + categoryName));
                questionDao.add(new Question(questionName, category));
                break;
            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command %s",
                        command.getAction(), command.getCommand()));
            }
        }

    }
}
