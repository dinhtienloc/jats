package vn.locdt.jats.addon.question.element.question;

import vn.locdt.jats.addon.question.element.item.Choice;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Choice {
    private List<Integer> activedIndexes;

    public MultipleChoiceQuestion(String title, String name) {
        super(title, name);
        this.activedIndexes = new ArrayList<>();
    }
}
