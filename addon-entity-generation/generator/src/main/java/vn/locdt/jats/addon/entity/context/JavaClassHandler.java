package vn.locdt.jats.addon.entity.context;

/**
 * Created by locdt on 2/9/2018.
 */
public interface JavaClassHandler {
    String getPackage();

    String getImports();

    String getClassName();

    String getExtendStatement();

    String getImplementStatement();

    String importClass(String canonicalName);
}
