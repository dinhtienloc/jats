package vn.locdt.jats.addon.question.element.question;

import vn.locdt.jats.addon.question.answer.Answer;
import vn.locdt.jats.addon.question.element.RenderElement;
import vn.locdt.jats.addon.question.element.item.Item;
import vn.locdt.jats.addon.question.exception.ConsoleNotInitializeException;

import java.io.IOException;

public abstract class Question<T extends Item> extends RenderElement {
    protected T item;
    protected boolean isPrintedResult = true;
    protected Answer answer;

    public Question() {}

    public Question(boolean isPrintedResult) {
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

    @Override
    public void updateRenderHeight() {
        setRenderHeight(getItem().getRenderHeight());
    }
}
