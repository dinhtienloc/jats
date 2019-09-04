package vn.locdt.jats.bundle.question;

import java.util.List;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;
import vn.locdt.jats.bundle.question.element.question.ConfirmQuestion;
import vn.locdt.jats.bundle.question.element.question.InputQuestion;
import vn.locdt.jats.bundle.question.element.question.Question;
import vn.locdt.jats.bundle.question.element.question.SingleChoiceQuestion;

public class JQuestion {
	private static NonBlockingReader nonBlockingReader;

	public static InputQuestion input(LineReader reader, String title) {
		return new InputQuestion(reader, title);
	}

	public static SingleChoiceQuestion select(LineReader reader, String title, String[] selection) {
		return new SingleChoiceQuestion(reader, title, null, selection);
	}

	public static SingleChoiceQuestion select(LineReader reader, String title, List<String> selection) {
		return new SingleChoiceQuestion(reader, title, null, selection);
	}

	public static Question confirm(LineReader reader, String title) {
		return new ConfirmQuestion(reader, title);

	}

	public static NonBlockingReader startCharacterReader(LineReader reader) {
		Terminal terminal = reader.getTerminal();
		terminal.enterRawMode();
		nonBlockingReader = terminal.reader();
		return nonBlockingReader;
	}

	public static void stopCharacterReader() {
		nonBlockingReader = null;
	}
}
