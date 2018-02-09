package vn.locdt.jats.core.question.element.question;

import jline.console.ConsoleReader;
import vn.locdt.jats.core.question.JQuestion;
import vn.locdt.jats.core.question.answer.Answer;
import vn.locdt.jats.core.question.element.item.Input;
import vn.locdt.jats.core.question.exception.ConsoleNotInitializeException;
import vn.locdt.jats.core.question.exception.UndefinedQuestionException;
import vn.locdt.jats.core.question.listener.InputListener;
import vn.locdt.jats.core.question.util.ConsoleUtils;
import vn.locdt.jats.core.question.event.InputEvent;

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
