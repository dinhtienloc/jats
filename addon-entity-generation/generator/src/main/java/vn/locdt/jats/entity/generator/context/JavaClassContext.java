package vn.locdt.jats.entity.generator.context;

import vn.locdt.jats.module.modeling.model.Table;
import vn.locdt.jats.module.generator.FileType;
import vn.locdt.jats.module.generator.context.GenerationContext;
import vn.locdt.jats.util.common.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by locdt on 2/9/2018.
 */
public abstract class JavaClassContext extends GenerationContext<Table> implements JavaClassContextHandler {
    protected static final String IMPORT_STATEMENT = "import %s;";
    protected static final String PACKAGE_STATEMENT = "package %s;";
    protected static final String EXTEND_STATEMENT = "extends %s;";
    protected static final String IMPLEMENT_STATEMENT = "implements %s;";

    protected Map<String, String> importsMapping;
    protected String packageName;

    public JavaClassContext(String rootPackage, String projectPath, String outputName, String packageName) {
        super(rootPackage, projectPath, outputName, FileType.JAVA);
        this.importsMapping = new HashMap<>();
        this.packageName = packageName;
    }

    public JavaClassContext(Table contextModel, String rootPackage, String projectPath, String outputName, String packageName) {
        this(rootPackage, projectPath, outputName, packageName);
        this.contextModel = contextModel;
    }

    public JavaClassContext(Table contextModel) {
        this();
        this.contextModel = contextModel;
    }

    public JavaClassContext() {
        super(FileType.JAVA);
        this.importsMapping = new HashMap<>();
    }

    @Override
    public String getPackageStatement() {
        if (packageName.length() > 0) {
            return String.format(PACKAGE_STATEMENT, packageName);
        }
        return "";
    }

    @Override
    public String getImports() {
        return importsMapping.entrySet()
                .stream()
                .filter( e -> !e.getKey().equals(e.getValue()) && !e.getValue().contains("<"))
                .map(e -> String.format(IMPORT_STATEMENT, e.getValue()))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String importClass(String canonicalName) {
        if (canonicalName.indexOf("<") > 0)
            return parsingIterableClass(canonicalName);
        else
            return put(StringUtils.getSimpleName(canonicalName));
    }

    private String parsingIterableClass(String name) {
        String elementClass = name.substring(name.indexOf("<") + 1, name.length() - 1);
        String iterableClass = name.substring(0, name.indexOf("<"));

        String[] iterableData = StringUtils.getSimpleName(iterableClass);
        String[] elementData = StringUtils.getSimpleName(elementClass);

        String renderIterableClass = put(iterableData);
        String renderElementClass = put(elementData);

        String simpleValue = renderIterableClass + "<" + renderElementClass + ">";
        importsMapping.put(simpleValue, name);
        return simpleValue;
    }

    private String put(String[] data) {
        String simpleName = data[0];
        String canonicalName = data[1];

        String existCanonicalName = importsMapping.get(simpleName);
        if (existCanonicalName != null) {
            if (!existCanonicalName.equals(canonicalName)) {
                importsMapping.put(canonicalName, canonicalName);
                return canonicalName;
            }
        }
        else {
            importsMapping.put(simpleName, canonicalName);
        }
        return simpleName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
