package vn.locdt.jats.bundle.question.answer;

import vn.locdt.jats.bundle.question.exception.UndefinedQuestionException;
import vn.locdt.jats.bundle.question.element.item.Item;

public class Answer {
    private Item sourceItem;
    private String value = "";

    public Answer(Item sourceItem) {
        this.sourceItem = sourceItem;
    }

    public Answer(Item sourceItem, String value) {
        this.sourceItem = sourceItem;
        this.value = value;
    }

    public String getName() {
        return this.sourceItem.getName();
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{\""+ this.sourceItem.getName() + ":" + "\"" + this.value + "\"}";
    }
}
