package cli;

import vn.locdt.JQuestion;

/**
 * Created by locdt on 1/21/2018.
 */
public abstract class QuestionCLI {
    protected JQuestion jQuestion;
    protected QuestionCLI nextQuestion;

    public QuestionCLI(JQuestion jQuestion) {
        this.jQuestion = jQuestion;
    }

    protected abstract void preQuestion();
    protected abstract void postQuestion();
    protected abstract void run();

    public void start() {
        preQuestion();
        run();
        postQuestion();
        nextQuestion.start();
    }
}
