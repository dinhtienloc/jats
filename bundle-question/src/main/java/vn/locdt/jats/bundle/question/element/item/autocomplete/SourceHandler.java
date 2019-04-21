package vn.locdt.jats.bundle.question.element.item.autocomplete;

import java.util.List;

public interface SourceHandler<T> {

    List<T> load(String input);

    String convert(T element);
}
