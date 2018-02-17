package vn.locdt.jats.module.shell.question;

/**
 * Created by locdt on 1/21/2018.
 */
public abstract class QuestionCLI {
    protected QuestionStatus status;

    public QuestionCLI() {}

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
}
