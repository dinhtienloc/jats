package vn.locdt.jats.addon.question.element.question;

import vn.locdt.jats.addon.question.JQuestion;
import vn.locdt.jats.addon.question.answer.Answer;
import vn.locdt.jats.addon.question.event.NonBlockInputEvent;
import vn.locdt.jats.addon.question.listener.NonBlockInputListener;
import vn.locdt.jats.addon.question.util.ConsoleUtils;
import vn.locdt.jats.addon.question.exception.ConsoleNotInitializeException;

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
        while (true) {
            input = JQuestion.getConsole().readCharacter();
            finished = onInput(new NonBlockInputEvent(input));
            if (finished) break;
        }

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
