package vn.locdt.jats.bundle.question.element.item;

public class Input extends Item {
    public Input(String title, String name, String value) {
        super(title, name, value);
        this.setRenderHeight(1);
    }

    public Input(String title, String name) {
        this(title, name, "");
    }

    @Override
    public void updateRenderHeight() {
        this.setRenderHeight(1);
    }
}
