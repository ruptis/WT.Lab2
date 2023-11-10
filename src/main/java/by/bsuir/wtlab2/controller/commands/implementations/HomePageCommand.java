package by.bsuir.wtlab2.controller.commands.implementations;

import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.JspResult;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.model.Question;
import by.bsuir.wtlab2.model.Topic;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@WebCommand(mapping = "/")
public class HomePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        Topic[] topics = getTopics();
        Question[] questions = getQuestions();

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("topics", topics);
        attributes.put("questions", questions);
        return new JspResult("index", attributes);
    }

    private static Topic[] getTopics() {
        Topic topic = new Topic(1, "Java", 10);
        Topic topic2 = new Topic(2, "C++", 5);
        Topic topic3 = new Topic(3, "C#", 7);
        return new Topic[]{topic, topic2, topic3};
    }

    private static Question[] getQuestions() {
        Question question = new Question(1, "How to create a class?", "I want to create a class in Java", 10, 5, "user1", "Java");
        Question question2 = new Question(2, "How to create a class?", "I want to create a class in Java", 10, 5, "user2", "Java");
        Question question3 = new Question(3, "How to create a class?", "I want to create a class in Java", 10, 5, "user3", "Java");
        Question question4 = new Question(4, "How to create a class?", "I want to create a class in Java", 10, 5, "user4", "Java");
        Question question5 = new Question(5, "How to create a class?", "I want to create a class in Java", 10, 5, "user5", "Java");
        return new Question[]{question, question2, question3, question4, question5};
    }
}
