package vn.locdt.jats.module.shell.exception;

public class QuestionDeclareNotConsistent extends RuntimeException {
    public QuestionDeclareNotConsistent(String message) {
        super(message);
    }

    public QuestionDeclareNotConsistent(String message, Throwable cause) {
        super(message, cause);
    }
}
