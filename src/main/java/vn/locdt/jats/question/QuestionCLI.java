package vn.locdt.jats.question;

import vn.locdt.jats.config.Configuration;
import vn.locdt.jats.config.ConfigurationData;

/**
 * Created by locdt on 1/21/2018.
 */
public abstract class QuestionCLI {
    public QuestionCLI() {}

    protected abstract RunStatus preQuestion();
    protected abstract RunStatus postQuestion();
    protected abstract RunStatus run();

    public void start() {
        RunStatus status = preQuestion();
        if (status == RunStatus.CONTINUE) {
            status = run();
            if (status == RunStatus.CONTINUE)
                postQuestion();
        }

    }

    protected enum RunStatus {
        CONTINUE,
        FINISHED
    }
}
