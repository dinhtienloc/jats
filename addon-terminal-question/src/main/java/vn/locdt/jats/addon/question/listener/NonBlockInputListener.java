package vn.locdt.jats.addon.question.listener;

import vn.locdt.jats.addon.question.event.NonBlockInputEvent;

@FunctionalInterface
public interface NonBlockInputListener extends Listener {
    boolean onInput(NonBlockInputEvent e);
}
