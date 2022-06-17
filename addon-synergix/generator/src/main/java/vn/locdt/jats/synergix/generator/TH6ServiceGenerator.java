package vn.locdt.jats.synergix.generator;

import vn.locdt.jats.module.generator.Generator;
import vn.locdt.jats.synergix.generator.context.TH6ServiceContext;

public class TH6ServiceGenerator extends Generator<TH6ServiceContext> {
    public TH6ServiceGenerator(TH6ServiceContext context) {
        super(context);
        this.templateName = "Service.ftl";
    }
}
