package vn.locdt.jats.bundle.question.element.question;

import org.jline.reader.LineReader;
import vn.locdt.jats.bundle.question.element.item.Input;
import vn.locdt.jats.bundle.question.event.InputEvent;
import vn.locdt.jats.bundle.question.listener.InputListener;
import vn.locdt.jats.bundle.question.util.ConsoleUtils;

public abstract class BaseInputQuestion<V> extends Question<Input, V> implements InputListener<V> {

    BaseInputQuestion(LineReader reader, String title) {
        super(reader, new Input(title, null), true);
    }

    @Override
    public V prompt() {
        String title = ConsoleUtils.createTitle(this.item.getTitle());
        String result = this.lineReader.readLine(title + " ");
        return this.onInput(new InputEvent(result));
    }

    protected abstract V convertInput(String inputValue);

    @Override
    public V onInput(InputEvent e) {
        V value = this.convertInput(e.getInputValue());
        if (value != null) {
            this.setValue(value);
            if (this.isPrintedResult) ConsoleUtils.printResult(this);
            return value;
        } else {
            // the answer has a problem (wrong, null,...), rerender the question
            return this.prompt();
        }
    }
}
