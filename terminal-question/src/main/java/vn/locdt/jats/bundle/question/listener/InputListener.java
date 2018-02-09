package vn.locdt.jats.core.question.listener;

import vn.locdt.jats.core.question.answer.Answer;
import vn.locdt.jats.core.question.event.InputEvent;

@FunctionalInterface
public interface InputListener extends Listener {
    Answer onInput(InputEvent e);
}
