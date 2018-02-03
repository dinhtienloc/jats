package vn.locdt.jats.addon.entity.modeling.exception;

/**
 * Created by locdt on 2/3/2018.
 */
@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}
