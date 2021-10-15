package pl.markon.ideas.handlers;

import pl.markon.ideas.dao.CategoryDao;
import pl.markon.ideas.dao.QuestionDao;
import pl.markon.ideas.input.UserInputCommand;
import pl.markon.ideas.model.Answer;
import pl.markon.ideas.model.Category;
import pl.markon.ideas.model.Question;

import java.util.List;
import java.util.logging.Logger;

public class AnswerCommandHandler extends BaseCommandHandler {
    private static Logger LOG = Logger.getLogger(AnswerCommandHandler.class.getName());
    public static final String COMMAND_NAME = "answer";

    private QuestionDao questionDao;
    private CategoryDao categoryDao;

    public AnswerCommandHandler() {
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
                LOG.info("List of answers...");
                if (command.getParam().size() != 1) {
                    throw new IllegalArgumentException("questions' list does't support any additional params");
                }
                String questionName = command.getParam().get(0);
                Question question = questionDao.findOne(questionName)
                        .orElseThrow(() -> new IllegalArgumentException("question not found " + questionName));

                displayQuestion(question);
                break;
            case ADD:
                //System.out.println("Add question ..");
                LOG.info("Add answer..");
                //TODO validate params
                if (command.getParam().size() != 2) {
                    throw new IllegalArgumentException("wrong command format, check help for more information");
                }
                questionName = command.getParam().get(0);
                String answerName = command.getParam().get(1);
                question = questionDao.findOne(questionName)
                        .orElseThrow(() -> new IllegalArgumentException("question not found " + questionName));
                questionDao.addAnswer(question,new Answer(answerName));

//                Category category = categoryDao.findOne(categoryName)
//                        .orElseThrow(() -> new IllegalArgumentException("Category not found " + categoryName));
//                questionDao.add(new Question(questionName, category));
                break;
            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command %s",
                        command.getAction(), command.getCommand()));
            }
        }

    }

    private void displayQuestion(Question question) {
        System.out.println(question.getName());
        question.getAnswers().forEach(System.out::println);
    }
}
