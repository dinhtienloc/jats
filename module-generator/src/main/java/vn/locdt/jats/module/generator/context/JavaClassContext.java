package vn.locdt.jats.module.generator.context;

import vn.locdt.jats.module.generator.FileType;
import vn.locdt.jats.util.common.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by locdt on 2/9/2018.
 */
public abstract class JavaClassContext<CM> extends GenerationContext<CM> implements JavaClassContextHandler {
    protected static final String IMPORT_STATEMENT = "import %s;";
    protected static final String PACKAGE_STATEMENT = "package %s;";
    protected static final String EXTEND_STATEMENT = " extends %s";
    protected static final String IMPLEMENT_STATEMENT = " implements %s";

    protected Map<String, String> importsMapping;
    protected String packageName;

    public JavaClassContext(String outputPath, String outputName, String packageName) {
        super(outputPath, outputName, FileType.JAVA);
        this.importsMapping = new LinkedHashMap<>();
        this.packageName = packageName;
    }

    public JavaClassContext(CM contextModel, String outputPath, String outputName, String packageName) {
        this(outputPath, outputName, packageName);
        this.contextModel = contextModel;
    }

    public JavaClassContext(CM contextModel) {
        this();
        this.contextModel = contextModel;
    }

    public JavaClassContext() {
        super(FileType.JAVA);
        this.importsMapping = new HashMap<>();
    }

    @Override
    public String getPackageStatement() {
        if (this.packageName.length() > 0) {
            return String.format(PACKAGE_STATEMENT, this.packageName);
        }
        return "";
    }

    @Override
    public String getImports() {
        return this.importsMapping.entrySet()
                .stream()
                .filter( e -> !e.getKey().equals(e.getValue()) && !e.getValue().contains("<"))
                .map(e -> String.format(IMPORT_STATEMENT, e.getValue()))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String importClass(String canonicalName) {
        if (canonicalName.indexOf("<") > 0)
            return this.parsingIterableClass(canonicalName);
        else
            return this.put(StringUtils.getSimpleName(canonicalName));
    }

    public String canoncialImportClass(String canonicalName) {
        this.importClass(canonicalName);
        return canonicalName;
    }
    public void silentImport(String... canonicalNames) {
        for (String canonicalName : canonicalNames) {
            this.importClass(canonicalName);
        }
    }

    private String parsingIterableClass(String name) {
        String elementClass = name.substring(name.indexOf("<") + 1, name.length() - 1);
        String iterableClass = name.substring(0, name.indexOf("<"));

        String[] iterableData = StringUtils.getSimpleName(iterableClass);
        String[] elementData = StringUtils.getSimpleName(elementClass);

        String renderIterableClass = this.put(iterableData);
        String renderElementClass = this.put(elementData);

        String simpleValue = renderIterableClass + "<" + renderElementClass + ">";
	    this.importsMapping.put(simpleValue, name);
        return simpleValue;
    }

    private String put(String[] data) {
        String simpleName = data[0];
        String canonicalName = data[1];

        String existCanonicalName = this.importsMapping.get(simpleName);
        if (existCanonicalName != null) {
            if (!existCanonicalName.equals(canonicalName)) {
	            this.importsMapping.put(canonicalName, canonicalName);
                return canonicalName;
            }
        }
        else {
	        this.importsMapping.put(simpleName, canonicalName);
        }
        return simpleName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String getExtendStatement() {
        return this.getExtendClassName() == null ? "" : String.format(EXTEND_STATEMENT, this.getExtendClassName());
    }

    @Override
    public String getImplementStatement() {
        return this.getImplementClassName() == null ? "" : String.format(IMPLEMENT_STATEMENT, this.getImplementClassName());
    }
}
