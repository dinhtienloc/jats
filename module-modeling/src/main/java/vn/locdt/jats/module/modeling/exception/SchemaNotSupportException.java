package vn.locdt.jats.module.modeling.exception;

/**
 * Created by locdt on 2/1/2018.
 */
public class SchemaNotSupportException extends Exception {
    public SchemaNotSupportException(String message) {
        super(message);
    }

    public SchemaNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
