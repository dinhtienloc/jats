package vn.locdt.jats.module.shell.question;

import org.jline.reader.LineReader;

/**
 * Created by locdt on 1/21/2018.
 */
public abstract class QuestionCLI {
    protected QuestionStatus status;
    protected LineReader lineReader;

    public QuestionCLI() {
    }

    protected void preQuestion() {
        status = QuestionStatus.CONTINUE;
    }

    protected void postQuestion() {
        if (status != QuestionStatus.STOP)
            status = QuestionStatus.FINISHED;
    }

    protected abstract void run();

    public QuestionStatus start() {
        preQuestion();
        if (status == QuestionStatus.CONTINUE) {
            run();
            if (status == QuestionStatus.CONTINUE)
                postQuestion();
        }
        return status;
    }

    public LineReader getLineReader() {
        return lineReader;
    }

    public void setLineReader(LineReader lineReader) {
        this.lineReader = lineReader;
    }
}
