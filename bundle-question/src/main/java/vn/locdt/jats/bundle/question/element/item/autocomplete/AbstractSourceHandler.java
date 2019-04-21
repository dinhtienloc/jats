package vn.locdt.jats.bundle.question.element.item.autocomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractSourceHandler<T> implements SourceHandler<T> {
    public List<String> loadAvaiableSource(String input) {
        List<T> rawSources = this.load(input);
        if (rawSources == null)
            return new ArrayList<>();
        else
            return rawSources.stream().map(this::convert).collect(Collectors.toList());
    }
}
