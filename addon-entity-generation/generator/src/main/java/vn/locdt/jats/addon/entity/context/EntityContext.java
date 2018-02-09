package vn.locdt.jats.addon.entity.context;

import vn.locdt.jats.addon.entity.FileType;
import vn.locdt.jats.addon.entity.StringUtils;
import vn.locdt.jats.addon.entity.modeling.model.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by locdt on 2/9/2018.
 */
public class EntityContext extends JavaClassContext {
    private Table model;

    public EntityContext(Table model, String outputDir, String outputName) {
        super(outputDir, outputName);
        this.model = model;
    }

    public EntityContext(Table model) {
        super();
        this.model = model;
    }

    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public String getExtensionClass() {
        return null;
    }

    @Override
    public String getImplementClasses() {
        return null;
    }


}
