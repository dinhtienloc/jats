package vn.locdt.jats.question;

/**
 * Created by locdt on 1/21/2018.
 */
public abstract class QuestionCLI {
    public QuestionCLI() {}

    protected abstract QuestionStatus preQuestion();
    protected abstract QuestionStatus postQuestion();
    protected abstract QuestionStatus run();

    public QuestionStatus start() {
        QuestionStatus status = preQuestion();
        if (status == QuestionStatus.CONTINUE) {
            status = run();
            if (status == QuestionStatus.CONTINUE)
                status = postQuestion();
        }
        return status;
    }
}
