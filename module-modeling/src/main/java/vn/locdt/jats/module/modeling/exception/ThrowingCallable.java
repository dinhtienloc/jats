package vn.locdt.jats.module.modeling.exception;

/**
 * Created by locdt on 2/3/2018.
 */
@FunctionalInterface
public interface ThrowingCallable<V, E extends Exception> {
    V call() throws E;
}

