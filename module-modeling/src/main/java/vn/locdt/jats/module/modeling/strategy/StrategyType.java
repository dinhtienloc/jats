package vn.locdt.jats.module.modeling.strategy;

/**
 * Created by locdt on 2/12/2018.
 */
public enum StrategyType {
    IDENTITY(null),
    SEQUENCE("sequence"),
    ENHANCED_SEQUENCE("enhanced-sequence"),
    TABLE(null);
    private String strategyName;

    StrategyType(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyName() {
        return this.strategyName;
    }
}
