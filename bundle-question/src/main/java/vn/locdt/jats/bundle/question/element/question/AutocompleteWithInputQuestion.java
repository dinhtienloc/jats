package vn.locdt.jats.bundle.question.element.question;

import org.jline.keymap.BindingReader;
import org.jline.reader.Binding;
import org.jline.reader.Buffer;
import org.jline.reader.LineReader;
import org.jline.reader.Widget;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.utils.NonBlockingReader;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.element.item.autocomplete.SourceHandler;
import vn.locdt.jats.bundle.question.event.InputEvent;
import vn.locdt.jats.bundle.question.event.NonBlockInputEvent;
import vn.locdt.jats.bundle.question.exception.ConsoleNotInitializeException;
import vn.locdt.jats.bundle.question.listener.InputListener;
import vn.locdt.jats.bundle.question.listener.InputValueChangeListener;
import vn.locdt.jats.bundle.question.util.ConsoleUtils;

import java.io.IOException;

public class AutocompleteWithInputQuestion extends SingleChoiceQuestion implements InputListener {
    private SourceHandler sourceHandler;

    public AutocompleteWithInputQuestion(String title, String name, boolean isPrintedResult, SourceHandler sourceHandler) {
        super(title, name, isPrintedResult);
    }

    public AutocompleteWithInputQuestion(String title, String name, SourceHandler sourceHandler) {
        this(title, name, true, sourceHandler);
    }

    public AutocompleteWithInputQuestion(String title, SourceHandler sourceHandler) {
        this(title, null, sourceHandler);
    }

    @Override
    public Answer prompt() throws IOException, ConsoleNotInitializeException {
        ConsoleUtils.renderQuestion(this);

        boolean finished;
        BindingReader bindingReader = new BindingReader(this.lineReader.getTerminal().reader());

        while (true) {
            Binding binding =  bindingReader.readBinding(this.lineReader.getKeys());
            System.out.println(binding);
            int c = ((LineReaderImpl)this.lineReader).readCharacter();
            if (c == 10 || c == 13) break;

            finished = onInput(new NonBlockInputEvent(c));
            if (finished) break;
        }

        this.lineReader.getWidgets().put(LineReader.REDISPLAY, () -> {
            updateBuffer();
            return true;
        });
        return this.answer;
    }

    @Override
    protected boolean handleInput(int charCode, NonBlockInputEvent e) {
        String c = new String(Character.toChars(charCode));
        System.out.println(c);
        updateBuffer();
        return false;
    }

    @Override
    public Answer onInput(InputEvent e) {
//        this.sourceHandler
        return this.answer;
    }

    public void updateBuffer() {
        System.out.println(this.lineReader.getBuffer().toString());
        System.out.println("Update buffer");
        Buffer buf = this.lineReader.getBuffer();
        buf.up();
//        int crtCursor = buf.cursor();
        buf.write("aaaaa\nbbbb\ncccc\n");
//        this.lineReader.getTerminal().flush();
//        buf.move(crtCursor);
    }
}
