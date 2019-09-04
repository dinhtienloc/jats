package vn.locdt.jats.bundle.question.element.question;

import org.jline.reader.LineReader;
import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.element.RenderElement;
import vn.locdt.jats.bundle.question.element.item.Item;

public abstract class Question<T extends Item, V> extends RenderElement {
	protected T item;
	boolean isPrintedResult = true;
	protected V value;
	protected LineReader lineReader;

	public Question(LineReader reader, T item) {
		this.lineReader = reader;
		this.item = item;
	}

	public Question(LineReader reader, T item, boolean isPrintedResult) {
		this(reader, item);
		this.isPrintedResult = isPrintedResult;
	}

	public T getItem() {
		return this.item;
	}

	boolean isPrintedResult() {
		return this.isPrintedResult;
	}

	public abstract V prompt();

	public V getValue() {
		return this.value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public LineReader getLineReader() {
		return this.lineReader;
	}

	@Override
	public void updateRenderHeight() {
		this.setRenderHeight(this.getItem().getRenderHeight());
	}
}
