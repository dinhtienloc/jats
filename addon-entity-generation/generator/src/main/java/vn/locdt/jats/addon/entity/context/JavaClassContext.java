package vn.locdt.jats.addon.entity.context;

import vn.locdt.jats.addon.entity.FileType;
import vn.locdt.jats.addon.entity.modeling.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by locdt on 2/9/2018.
 */
public abstract class JavaClassContext extends GenerationContext implements JavaClassHandler {
    protected static final String IMPORT_STATEMENT = "import %s;";
    protected static final String PACKAGE_STATEMENT = "package %s;";
    protected static final String EXTEND_STATEMENT = "extends %s;";
    protected static final String IMPLEMENT_STATEMENT = "implements %s;";

    protected Map<String, String> importsMapping;

    public JavaClassContext(String outputDir, String outputName) {
        super(outputDir, outputName, FileType.JAVA);
        this.importsMapping = new HashMap<>();
    }

    public JavaClassContext() {
        super(FileType.JAVA);
        this.importsMapping = new HashMap<>();
    }

    @Override
    public String getPackage() {
        return String.format(PACKAGE_STATEMENT,
                StringUtils.getCanonicalNameFromPath(outputDir));
    }

    @Override
    public String getImports() {
        return importsMapping.entrySet()
                .stream()
                .filter( e -> !e.getKey().equals(e.getValue()))
                .map(e -> String.format(IMPORT_STATEMENT, e.getValue()))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String importClass(String canonicalName) {
        String[] data = StringUtils.getSimpleName(canonicalName);
        boolean printSimpleName = put(data);
        if (printSimpleName)
            return data[0];
        else
            return data[1];
    }

    private boolean put(String[] data) {
        String simpleName = data[0];
        String canonicalName = data[1];
        boolean printSimpleName;

        String existCanonicalName = importsMapping.get(simpleName);
        if (existCanonicalName != null) {
            if (existCanonicalName.equals(canonicalName))
                printSimpleName = true;
            else {
                printSimpleName = false;
                importsMapping.put(canonicalName, canonicalName);
            }
        }
        else {
            printSimpleName = true;
            importsMapping.put(simpleName, canonicalName);
        }
        return printSimpleName;
    }
}
