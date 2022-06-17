package vn.locdt.jats.bundle.question.answer;

import vn.locdt.jats.bundle.question.element.item.Item;

public class Answer<V> {
    private Item sourceItem;
    private V value;

    public Answer(Item sourceItem) {
        this.sourceItem = sourceItem;
    }

    public Answer(Item sourceItem, V value) {
        this.sourceItem = sourceItem;
        this.value = value;
    }

    public String getName() {
        return this.sourceItem.getName();
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{\"" + this.sourceItem.getName() + ":" + "\"" + this.value + "\"}";
    }
}
