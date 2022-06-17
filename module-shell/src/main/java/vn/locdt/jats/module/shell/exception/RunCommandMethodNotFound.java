package vn.locdt.jats.module.shell.exception;

public class RunCommandMethodNotFound extends RuntimeException {
    public RunCommandMethodNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public RunCommandMethodNotFound(String message) {
        super(message);
    }
}
