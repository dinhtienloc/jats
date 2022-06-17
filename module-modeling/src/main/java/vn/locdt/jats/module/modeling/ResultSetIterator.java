package vn.locdt.jats.module.modeling;

import vn.locdt.jats.util.common.LogUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by locdt on 2/2/2018.
 */
public class ResultSetIterator<T> implements Iterable<T> {
    private final ResultSet rs;
    private final Function<ResultSet, T> onNextFnc;

    public ResultSetIterator(ResultSet rs) {
        this.rs = rs;
        this.onNextFnc = null;
    }

    public ResultSetIterator(ResultSet rs, Function<ResultSet, T> onNextFnc) {
        this.rs = rs;
        this.onNextFnc = onNextFnc;
    }

    public ResultSet getResultSet() {
        return this.rs;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        try {
            return new Iterator<T>() {
                Boolean hasNext = ResultSetIterator.this.rs.next();

                @Override
                public boolean hasNext() {
                    return this.hasNext;
                }

                @Override
                public T next() {
                    T result = null;
                    if (ResultSetIterator.this.onNextFnc == null) {
                        try {
                            result = (T) ResultSetIterator.this.rs;
                        } catch (ClassCastException e) {
                            LogUtils.printErrorLog("Need to declare method to transform ResultSet to another class", e);
                        }
                    } else {
                        result = ResultSetIterator.this.onNextFnc.apply(ResultSetIterator.this.rs);
                    }
                    try {
                        this.hasNext = ResultSetIterator.this.rs.next();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return result;
                }
            };
        } catch (Exception e) {
            //you should add proper exception handling here
            throw new RuntimeException(e);
        }
    }

    public void forEach(Consumer<? super T> action) {
        try {
            Objects.requireNonNull(action);
            while (this.rs.next()) {
                action.accept((T) this.rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    public static ResultSetIterator<ResultSet> wrap(ResultSet rs) {
        return new ResultSetIterator<>(rs);
    }
}
