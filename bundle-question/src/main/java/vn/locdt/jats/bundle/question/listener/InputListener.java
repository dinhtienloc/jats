package vn.locdt.jats.bundle.question.listener;

import vn.locdt.jats.bundle.question.event.InputEvent;

@FunctionalInterface
public interface InputListener<V> extends Listener {
    V onInput(InputEvent e);
}
