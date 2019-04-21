package vn.locdt.jats.bundle.question.element.item.choice;

import vn.locdt.jats.bundle.question.element.item.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Choice extends Item {
    public static String activedPrefix = "> ";
    public static String deactivedPrefix = "  ";
    protected List<Selector> selectors;

    public Choice(String title, String name) {
        super(title, name, "");
        this.selectors = new ArrayList<>();
    }

    public Choice(String title, String name, String[] selectors) {
        super(title, name, "");
        this.selectors = new ArrayList<>();
        for (String s : selectors) {
            this.selectors.add(new Selector(s));
        }
        updateRenderHeight();
    }

    public List<Selector> getSelectors() {
        return this.selectors;
    }

    public void setSelectors(List<Selector> selectors) {
        this.selectors = selectors;
        for (Selector selector : this.selectors) {
            selector.setPrefix(deactivedPrefix);
        }
    }

    public void addSelector(Selector selector) {
        if (selector != null) {
            selector.setPrefix(deactivedPrefix);
            this.selectors.add(selector);
            updateRenderHeight();
        }
    }

    public void addSelector(String selector) {
        if (selector != null) {
            Selector newSelector = new Selector(selector);
            newSelector.setPrefix(deactivedPrefix);
            this.selectors.add(newSelector);
            updateRenderHeight();
        }
    }

    public void addSelectors(List<Selector> selectors) {
        this.selectors.addAll(selectors);
        updateRenderHeight();
    }

    public void addSelectors(String[] selectors) {
        for (String s : selectors)
            addSelector(s);
        updateRenderHeight();
    }

    @Override
    public void updateRenderHeight() {
        setRenderHeight(this.selectors.size());
    }
}
