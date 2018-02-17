package vn.locdt.jats.bundle.question.element.question;

import vn.locdt.jats.bundle.question.element.item.Choice;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Choice {
    private List<Integer> activedIndexes;

    public MultipleChoiceQuestion(String title, String name) {
        super(title, name);
        this.activedIndexes = new ArrayList<>();
    }
}
