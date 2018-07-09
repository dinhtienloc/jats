package vn.locdt.jats.module.generator.exception;

/**
 * Created by locdt on 2/10/2018.
 */
public class GeneratorException extends RuntimeException {
    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
