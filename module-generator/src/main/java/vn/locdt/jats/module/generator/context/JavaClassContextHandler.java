package vn.locdt.jats.module.generator.context;

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

    String getExtendClassName();

    String getImplementClassName();
}
