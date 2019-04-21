package vn.locdt.jats.bundle.question.element.question;

import org.jline.reader.LineReader;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.bundle.question.answer.Answer;
import vn.locdt.jats.bundle.question.event.InputEvent;
import vn.locdt.jats.bundle.question.exception.ConsoleNotInitializeException;
import vn.locdt.jats.bundle.question.util.ConsoleUtils;

import java.io.IOException;

public class PasswordQuestion extends InputQuestion {
	private Character mask;

	public PasswordQuestion(String title, String name, Character mask, boolean isPrintedResult) throws IOException {
		super(title, name, isPrintedResult);
		this.mask = mask;
	}

	public PasswordQuestion(String title, String name, Character mask) {
		super(title, name);
		this.mask = mask;
	}

	@Override
	public Answer prompt() throws IOException, ConsoleNotInitializeException {
		String title = ConsoleUtils.createTitle(item.getTitle());
		String result = this.lineReader.readLine(title + " ", mask);
		return onInput(new InputEvent(result));
	}

	public Character getMask() {
		return mask;
	}

	public void setMask(Character mask) {
		this.mask = mask;
	}
}
