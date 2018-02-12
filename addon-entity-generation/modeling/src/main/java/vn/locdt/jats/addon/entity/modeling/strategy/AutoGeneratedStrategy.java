package vn.locdt.jats.addon.entity.modeling.strategy;

/**
 * Created by locdt on 2/12/2018.
 */
public class AutoGeneratedStrategy {
    protected String name;
    protected StrategyType strategyType;

    public AutoGeneratedStrategy(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(StrategyType strategyType) {
        this.strategyType = strategyType;
    }
}
