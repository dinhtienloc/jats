package vn.locdt.jats.module.shell.exception;

public class CliOptionQuestionNotMapping extends RuntimeException {
    public CliOptionQuestionNotMapping(String message) {
        super(message);
    }

    public CliOptionQuestionNotMapping(String message, Throwable cause) {
        super(message, cause);
    }
}
