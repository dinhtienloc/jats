package vn.locdt.jats.bundle.question.element.question;

import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.event.InputEvent;
import vn.locdt.jats.bundle.question.exception.ConsoleNotInitializeException;
import vn.locdt.jats.bundle.question.exception.UndefinedQuestionException;
import jline.console.ConsoleReader;
import vn.locdt.jats.bundle.question.listener.InputListener;
import vn.locdt.jats.bundle.question.element.item.Input;
import vn.locdt.jats.bundle.question.util.ConsoleUtils;

import java.io.IOException;

public class InputQuestion extends Question implements InputListener {

    public InputQuestion(String title, String name, boolean isPrintedResult) throws IOException {
        super(isPrintedResult);
        this.item = new Input(title, name);
        try {
            this.answer = new Answer(item);
        } catch (UndefinedQuestionException e) {
            e.printStackTrace();
        }
    }

    public InputQuestion(String title, String name) {
        super();
        this.item = new Input(title, name);
        try {
            this.answer = new Answer(item);
        } catch (UndefinedQuestionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Answer prompt() throws IOException, ConsoleNotInitializeException {
        ConsoleReader console = JQuestion.getConsole();
        String title = ConsoleUtils.createTitle(item.getTitle());
        String result = console.readLine(title + " ");
        return onInput(new InputEvent(result));
    }

    @Override
    public Answer onInput(InputEvent e) {
        setAnswer(e.getInputValue());
        if (this.isPrintedResult) ConsoleUtils.printResult(this);
        return getAnswer();
    }
}
