package vn.locdt.jats.bundle.question.event;

import vn.locdt.jats.bundle.question.element.question.Question;

public abstract class ConsoleEvent {
    protected String value;
    protected Question firedQuestion;

    public String getValue() {
        return value;
    }

    public Question getFiredQuestion() {
        return firedQuestion;
    }
}
