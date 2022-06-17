package vn.locdt.jats.bundle.question.exception;

public class UnspecifiedAnswerException extends RuntimeException {
    public UnspecifiedAnswerException(String message) {
        super(message);
    }

    public UnspecifiedAnswerException(String message, Throwable cause) {
        super(message, cause);
    }
}
