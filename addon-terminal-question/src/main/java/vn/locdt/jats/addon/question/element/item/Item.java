package vn.locdt.jats.addon.question.element.item;

import vn.locdt.jats.addon.question.element.RenderElement;

public abstract class Item extends RenderElement {
    protected String name;
    protected String value;
    protected String title;

    public Item(String title, String name, String value) {
        this.title = title;
        this.name = name;
        this.value = value;
    }

    public Item(String title, String name) {
        this.title = title;
        this.name = name;
        this.value = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
