package vn.locdt.jats.bundle.question.element.item.choice;

public class Selector {
    private String prefix;
    private String value;

    public Selector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return this.prefix + this.value;
    }
}
