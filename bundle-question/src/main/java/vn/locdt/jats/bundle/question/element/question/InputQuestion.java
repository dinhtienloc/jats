package vn.locdt.jats.bundle.question.element.question;

import org.jline.reader.LineReader;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.event.InputEvent;
import vn.locdt.jats.bundle.question.exception.ConsoleNotInitializeException;
import vn.locdt.jats.bundle.question.exception.UndefinedQuestionException;

import vn.locdt.jats.bundle.question.listener.InputListener;
import vn.locdt.jats.bundle.question.element.item.Input;
import vn.locdt.jats.bundle.question.util.ConsoleUtils;

import java.io.IOException;

public class InputQuestion extends Question<Input> implements InputListener {

    public InputQuestion(String title, String name, boolean isPrintedResult) {
        super(new Input(title, name), isPrintedResult);
    }

    public InputQuestion(String title, String name) {
        this(title, name, false);
    }

    public InputQuestion(String title) {
        this(title, null);
    }

    @Override
    public Answer prompt() throws IOException, ConsoleNotInitializeException {
        String title = ConsoleUtils.createTitle(this.item.getTitle());
        String result = this.lineReader.readLine(title + " ");
        return this.onInput(new InputEvent(result));
    }

    @Override
    public Answer onInput(InputEvent e) {
	    this.setAnswer(e.getInputValue());
        if (this.isPrintedResult) ConsoleUtils.printResult(this);
        return this.getAnswer();
    }
}
