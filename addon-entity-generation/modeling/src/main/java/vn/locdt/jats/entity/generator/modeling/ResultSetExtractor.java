package vn.locdt.jats.entity.generator.modeling;

import vn.locdt.jats.entity.generator.modeling.model.*;

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

    public abstract Relation relation(ResultSet rs);
}
