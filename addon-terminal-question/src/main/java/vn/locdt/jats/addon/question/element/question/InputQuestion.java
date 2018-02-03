package vn.locdt.jats.addon.question.element.question;

import jline.console.ConsoleReader;
import vn.locdt.jats.addon.question.JQuestion;
import vn.locdt.jats.addon.question.answer.Answer;
import vn.locdt.jats.addon.question.element.item.Input;
import vn.locdt.jats.addon.question.event.InputEvent;
import vn.locdt.jats.addon.question.exception.UndefinedQuestionException;
import vn.locdt.jats.addon.question.listener.InputListener;
import vn.locdt.jats.addon.question.util.ConsoleUtils;
import vn.locdt.jats.addon.question.exception.ConsoleNotInitializeException;

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
        String result = console.readLine(item.getTitle() + " ");
        return onInput(new InputEvent(result));
    }

    @Override
    public Answer onInput(InputEvent e) {
        setAnswer(e.getInputValue());
        if (this.isPrintedResult) ConsoleUtils.printResult(this);
        return getAnswer();
    }
}
