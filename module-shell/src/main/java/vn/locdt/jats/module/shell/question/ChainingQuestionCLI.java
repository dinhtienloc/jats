package vn.locdt.jats.module.shell.question;

public abstract class ChainingQuestionCLI<P, R> extends QuestionCLI {
    private P parameter;


    protected P getParameter() {
        return parameter;
    }

    public void setParameter(P parameter) {
        this.parameter = parameter;
    }

    public abstract R getOutputData();
}
