package vn.locdt.jats.bundle.question.element.question;

import org.apache.commons.lang.StringUtils;
import org.jline.reader.LineReader;

public class InputQuestion extends BaseInputQuestion<String> {

	public InputQuestion(LineReader reader, String title) {
		super(reader, title);
	}

	@Override
	protected String convertInput(String inputValue) {
		return StringUtils.isEmpty(inputValue) ? "" : inputValue;
	}
}
