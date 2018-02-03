package vn.locdt.jats.addon.entity.modeling.exception;

/**
 * Created by locdt on 2/1/2018.
 */
public class CatalogNotSupportException extends Exception {
    public CatalogNotSupportException(String message) {
        super(message);
    }

    public CatalogNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
