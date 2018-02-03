package vn.locdt.jats.addon.question.element.item;

public class Input extends Item {
    public Input(String title, String name, String value) {
        super(title, name, value);
        setRenderHeight(1);
    }

    public Input(String title, String name) {
        this(title, name, "");
    }

    @Override
    public void updateRenderHeight() {
        setRenderHeight(1);
    }
}
