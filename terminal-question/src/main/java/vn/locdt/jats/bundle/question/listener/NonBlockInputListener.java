package vn.locdt.jats.core.question.listener;

import vn.locdt.jats.core.question.event.NonBlockInputEvent;

@FunctionalInterface
public interface NonBlockInputListener extends Listener {
    boolean onInput(NonBlockInputEvent e);
}
