package vn.locdt.jats.module.shell.command;

import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by locdt on 1/22/2018.
 */
@Component
public abstract class QuestionCommand {

    @Autowired
    @Lazy
    private LineReader lineReader;

    public QuestionCommand() {

    }

    public LineReader getLineReader() {
        return this.lineReader;
    }
}
