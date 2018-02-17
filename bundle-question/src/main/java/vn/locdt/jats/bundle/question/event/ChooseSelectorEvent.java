package vn.locdt.jats.bundle.question.event;

import vn.locdt.jats.bundle.question.element.item.Selector;

public class ChooseSelectorEvent {
    private Selector selector;

    public ChooseSelectorEvent(Selector selector) {
        this.selector = selector;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }
}
