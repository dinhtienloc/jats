package vn.locdt.jats.synergix.generator.context;

import vn.locdt.jats.module.generator.context.XHTMLContext;
import vn.locdt.jats.synergix.generator.context.model.SynergixFormModel;

public class TH6WebPageContext<M extends SynergixFormModel> extends XHTMLContext<M> {
    public TH6WebPageContext(M contextModel, String outputDirectory, String outputName) {
        super(contextModel, outputDirectory, outputName);
    }
}
