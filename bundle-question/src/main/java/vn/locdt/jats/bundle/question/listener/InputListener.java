package vn.locdt.jats.bundle.question.listener;

import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.event.InputEvent;

@FunctionalInterface
public interface InputListener extends Listener {
    Answer onInput(InputEvent e);
}
