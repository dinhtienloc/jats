package vn.locdt.jats.addon.question.listener;

import vn.locdt.jats.addon.question.answer.Answer;
import vn.locdt.jats.addon.question.event.InputEvent;

@FunctionalInterface
public interface InputListener extends Listener {
    Answer onInput(InputEvent e);
}
