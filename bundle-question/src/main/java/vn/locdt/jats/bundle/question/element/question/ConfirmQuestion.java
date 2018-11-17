package vn.locdt.jats.bundle.question.element.question;

import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;
import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.event.NonBlockInputEvent;
import vn.locdt.jats.bundle.question.exception.ConsoleNotInitializeException;
import vn.locdt.jats.bundle.question.listener.NonBlockInputListener;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.bundle.question.util.ConsoleUtils;

import java.io.IOException;

/**
 * Created by locdt on 1/10/2018.
 */
public class ConfirmQuestion extends Question implements NonBlockInputListener {

    @Override
    public Answer prompt() throws IOException, ConsoleNotInitializeException {
        ConsoleUtils.renderQuestion(this);

        // read input
        int input;
        boolean finished;

        NonBlockingReader nonBlockingReader = JQuestion.startCharacterReader();
        while (true) {
            input = nonBlockingReader.read();
            finished = onInput(new NonBlockInputEvent(input));
            if (finished) break;
        }
        JQuestion.stopCharacterReader();
        return this.answer;
    }

    private boolean handleInput(int charCode, NonBlockInputEvent e) {
        return false;
    }

    @Override
    public boolean onInput(NonBlockInputEvent e) {
        int charCode = e.getAddedChar();
//            System.out.println(charCode);
        return handleInput(charCode, e);
    }
}
