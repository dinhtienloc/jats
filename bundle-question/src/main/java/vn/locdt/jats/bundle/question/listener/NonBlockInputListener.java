package vn.locdt.jats.bundle.question.listener;

import vn.locdt.jats.bundle.question.event.NonBlockInputEvent;

@FunctionalInterface
public interface NonBlockInputListener extends Listener {
    boolean onInput(NonBlockInputEvent e);
}
