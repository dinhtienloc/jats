package vn.locdt.jats.addon.question;

import jline.console.ConsoleReader;
import vn.locdt.jats.addon.question.answer.Answer;
import vn.locdt.jats.addon.question.element.question.Question;
import vn.locdt.jats.addon.question.element.question.SingleChoiceQuestion;
import vn.locdt.jats.addon.question.element.question.InputQuestion;
import vn.locdt.jats.addon.question.exception.ConsoleNotInitializeException;

import java.io.IOException;
import java.util.*;

public class JQuestion {
    private static ConsoleReader console;
    private static QuestionGroup questionGroup;

    static {
        try {
            console = new ConsoleReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static QuestionGroup createQuestionGroup() {
        questionGroup = new QuestionGroup();
        return questionGroup;
    }

    public static Answer input(String title, String name) {
        try {
            return new InputQuestion(title, name).prompt();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer select(String title, String name, String[] selection) {
        try {
            return new SingleChoiceQuestion(title, name, selection).prompt();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer select(String title, String name, List<String> selection) {
        try {
            return new SingleChoiceQuestion(title, name, selection).prompt();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ConsoleReader getConsole() throws ConsoleNotInitializeException {
        if (console == null) throw new ConsoleNotInitializeException("Console is not initialized.");
        return console;
    }

    public static class QuestionGroup {
        private Map<String, String> resultMap;
        private List<Question> questions;

        public QuestionGroup() {
            this.questions = new ArrayList<>();
            this.resultMap = new LinkedHashMap<>();
        }

        public QuestionGroup addInputQuestion(String title, String name) {
            this.questions.add(new InputQuestion(title, name));
            return this;
        }

        public QuestionGroup addInputQuestion(InputQuestion inputQuestion) {
            this.questions.add(inputQuestion);
            return this;
        }

        public QuestionGroup addSingleChoiceQuestion(SingleChoiceQuestion singleChoiceQuestion) {
            this.questions.add(singleChoiceQuestion);
            return this;
        }

        public Map<String, String> prompt() throws IOException, ConsoleNotInitializeException {
            resultMap.clear();

            if (questions.size() == 0)
                return resultMap;

            for (Question q : questions) {
                Answer answer = q.prompt();
                resultMap.put(answer.getName(), answer.getValue());
//            System.out.println(answer);
            }

            return resultMap;
        }
    }
}
