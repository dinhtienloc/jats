package vn.locdt.jats.module.modeling.util;

import vn.locdt.jats.module.modeling.exception.ThrowingCallable;
import vn.locdt.jats.module.modeling.exception.ThrowingConsumer;

import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * Created by locdt on 2/3/2018.
 */
public class SQL {
    public static <T> Consumer<T> wrap(ThrowingConsumer<T, SQLException> throwingConsumer) {
        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    public static<T> T wrap(ThrowingCallable<T, SQLException> throwingCallable, Object returnWhenExceptionOccur) {
        try {
            return throwingCallable.call();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (T)returnWhenExceptionOccur;
    }

    public static<T> T wrap(ThrowingCallable<T, SQLException> throwingCallable) {
        return wrap(throwingCallable, null);
    }
}
