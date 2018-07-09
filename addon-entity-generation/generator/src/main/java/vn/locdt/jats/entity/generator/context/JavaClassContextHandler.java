package vn.locdt.jats.entity.generator.context;

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
