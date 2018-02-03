package vn.locdt.jats.addon.entity.modeling.exception;

/**
 * Created by locdt on 2/1/2018.
 */
public class SystemReaderNotFoundException extends RuntimeException {
    public SystemReaderNotFoundException(String message) {
        super(message);
    }

    public SystemReaderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
