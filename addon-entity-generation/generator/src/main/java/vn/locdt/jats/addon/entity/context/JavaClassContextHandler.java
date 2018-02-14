package vn.locdt.jats.addon.entity.context;

import vn.locdt.jats.addon.entity.modeling.model.Column;

/**
 * Created by locdt on 2/9/2018.
 */
public interface JavaClassContextHandler {

    String getPackageStatement();

    String getImports();

    String getClassName();

    String getExtendStatement();

    String getImplementStatement();

    String importClass(String canonicalName);

    default void importClassQuietly(String canonicalName) {
        importClass(canonicalName);
    }
}
