package vn.locdt.jats.bundle.question.element.question;

import org.apache.commons.lang.StringUtils;
import org.jline.reader.LineReader;

/**
 * Created by locdt on 1/10/2018.
 */
public class ConfirmQuestion extends BaseInputQuestion<Boolean> {
    private String yes = "Y";
    private String no = "N";

    public ConfirmQuestion(LineReader reader, String title) {
        super(reader, title);
    }

    public ConfirmQuestion yesLabel(String yes) {
        this.yes = yes;
        return this;
    }

    public ConfirmQuestion noLabel(String no) {
        this.no = no;
        return this;
    }

    @Override
    protected Boolean convertInput(String inputValue) {
        if (StringUtils.isNotEmpty(inputValue)) {
            if (inputValue.equalsIgnoreCase(this.yes)) {
                return true;
            } else if (inputValue.equalsIgnoreCase(this.no)) {
                return false;
            }
        }

        return null;
    }
}
