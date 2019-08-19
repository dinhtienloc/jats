package vn.locdt.jats.module.shell.question;

public abstract class ParameterizedQuestionCLI<P> extends QuestionCLI {
    protected P parameter;
    public ParameterizedQuestionCLI(P parameter) {
        this.parameter = parameter;
    }

    @Override
    protected void preQuestion() {
        if (this.validateParameters()) {
            status = QuestionStatus.CONTINUE;
        }
        else {
            status = QuestionStatus.STOP;
        }
    }

    protected boolean validateParameters() {
        return true;
    }

    protected P getParameter() {
        return parameter;
    }

    public void setParameter(P parameter) {
        this.parameter = parameter;
    }
}
