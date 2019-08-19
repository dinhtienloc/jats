package vn.locdt.jats.bundle.question.element.question;

import org.jline.reader.LineReader;
import org.jline.utils.NonBlockingReader;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.element.RenderElement;
import vn.locdt.jats.bundle.question.exception.ConsoleNotInitializeException;
import vn.locdt.jats.bundle.question.element.item.Item;
import vn.locdt.jats.bundle.question.spring.context.ShellApplicationContext;

import java.io.IOException;

public abstract class Question<T extends Item> extends RenderElement {
    protected T item;
    protected boolean isPrintedResult = true;
    protected Answer answer;
    protected LineReader lineReader;

    public Question(T item) {
        this.item = item;
        this.answer = new Answer(item);
    }

    public Question(T item, boolean isPrintedResult) {
        this(item);
        this.isPrintedResult = isPrintedResult;
    }

    public T getItem() {
        return item;
    }

    public boolean isPrintedResult() {
        return isPrintedResult;
    }

    public abstract Answer prompt() throws IOException, ConsoleNotInitializeException;

    public Answer getAnswer() {
        return this.answer;
    }

    public String getAnswerAsJson() {
        return answer.getName() + ":" + answer.getValue();
    }

    public String getAnswerValue() {
        return answer.getValue();
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setAnswer(String value) {
        this.answer.setValue(value);
    }

    public LineReader getLineReader() {
        return lineReader;
    }

    public Question lineReader(LineReader lineReader) {
        this.lineReader = lineReader;
        return this;
    }

    public NonBlockingReader enableCharacterReader() {
        return JQuestion.startCharacterReader(this.lineReader);
    }

    public void disableCharacterReader() {
        JQuestion.stopCharacterReader();
    }

    @Override
    public void updateRenderHeight() {
        setRenderHeight(getItem().getRenderHeight());
    }
}
