package vn.locdt.jats.addon.entity.modeling.model;

/**
 * Created by locdt on 2/1/2018.
 */
public abstract class Model {
    protected String name;
    protected String description;

    public Model() {}

    public Model(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
