package vn.locdt.jats.bundle.question;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;
import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.element.question.PasswordQuestion;
import vn.locdt.jats.bundle.question.element.question.Question;
import vn.locdt.jats.bundle.question.element.question.SingleChoiceQuestion;
import vn.locdt.jats.bundle.question.exception.ConsoleNotInitializeException;
import vn.locdt.jats.bundle.question.element.question.InputQuestion;

import java.io.IOException;
import java.util.*;

public class JQuestion {
    private static Terminal terminal;
    private static LineReader lineReader;
    private static NonBlockingReader nonBlockingReader;
    private static QuestionGroup questionGroup;

    static {
        try {
            terminal = TerminalBuilder.builder()
                    .jna(true).system(true)
                    .build();
            lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();
        }
        catch (IOException e) {
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
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer maskInput(String title, String name, Character mask) {
        try {
            return new PasswordQuestion(title, name, mask).prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer select(String title, String name, String[] selection) {
        try {
            return new SingleChoiceQuestion(title, name, selection).prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Answer select(String title, String name, List<String> selection) {
        try {
            return new SingleChoiceQuestion(title, name, selection).prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Terminal getTerminal() throws ConsoleNotInitializeException {
        if (terminal == null) throw new ConsoleNotInitializeException("Console is not initialized.");
        return terminal;
    }
    public static LineReader getLineReader() throws ConsoleNotInitializeException {
        if (lineReader == null) throw new ConsoleNotInitializeException("Console is not initialized.");
        return lineReader;
    }

    public static NonBlockingReader startCharacterReader() {
        terminal.enterRawMode();
        nonBlockingReader = terminal.reader();
        return nonBlockingReader;
    }

    public static void stopCharacterReader() {
        try {
            nonBlockingReader.close();
            nonBlockingReader = null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
