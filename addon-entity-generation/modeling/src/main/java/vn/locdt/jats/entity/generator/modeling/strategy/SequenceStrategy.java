package vn.locdt.jats.entity.generator.modeling.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by locdt on 2/12/2018.
 */
public class SequenceStrategy extends AutoGeneratedStrategy {
    private List<Parameter> parameters;

    public SequenceStrategy() {
        super(StrategyType.SEQUENCE);
        this.parameters = new ArrayList<>();
        this.name = "Sequence Strategy";
    }
}