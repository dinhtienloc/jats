package vn.locdt.jats.addon.entity.modeling.model;

import vn.locdt.jats.util.common.StringUtils;

/**
 * Created by locdt on 2/1/2018.
 */
public abstract class Model {
    protected String name;
    protected String description;
    protected String javaName;
    protected String javaVarName;

    public Model() {}

    public Model(String name) {
        this.name = name;
        this.javaName = StringUtils.convertSqlNameToClassName(name);
        this.javaVarName = StringUtils.convertCapitalizeFullyToVariableName(javaName);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getJavaName() {
        return javaName;
    }

    public String getJavaVarName() {
        return javaVarName;
    }
}
