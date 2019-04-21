package vn.locdt.jats.module.modeling.strategy;

/**
 * Created by locdt on 2/12/2018.
 */
public class IdentityStrategy extends AutoGeneratedStrategy {
    public IdentityStrategy() {
        super(StrategyType.IDENTITY);
        this.name = "Identity Strategy";
    }
}