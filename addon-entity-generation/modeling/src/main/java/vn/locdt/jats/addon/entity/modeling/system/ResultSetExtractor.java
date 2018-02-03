package vn.locdt.jats.addon.entity.modeling.system;

import vn.locdt.jats.addon.entity.modeling.model.*;

import java.sql.ResultSet;

/**
 * Created by locdt on 2/3/2018.
 */
public abstract class ResultSetExtractor {
    public abstract Catalog catalog(ResultSet rs);

    public abstract Schema schema(ResultSet rs);

    public abstract Table table(ResultSet rs);

    public abstract Column column(ResultSet rs);

    public abstract String primaryKey(ResultSet rs);

    public abstract ForeignKey foreignKey(ResultSet rs);
}
