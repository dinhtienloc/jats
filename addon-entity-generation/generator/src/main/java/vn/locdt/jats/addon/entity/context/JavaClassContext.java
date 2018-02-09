package vn.locdt.jats.addon.entity.context;

import vn.locdt.jats.addon.entity.FileType;
import vn.locdt.jats.addon.entity.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by locdt on 2/9/2018.
 */
public abstract class JavaClassContext extends GenerationContext implements JavaClassHandler {
    protected static final String IMPORT_STATEMENT = "import %s;";
    protected static final String PACKAGE_STATEMENT = "package %s;";
    protected List<String> importDeclarations;

    public JavaClassContext(String outputDir, String outputName) {
        super(outputDir, outputName, FileType.JAVA);
        this.importDeclarations = new ArrayList<>();
    }

    public JavaClassContext() {
        super(FileType.JAVA);
        this.importDeclarations = new ArrayList<>();
    }

    @Override
    public String getPackage() {
        return String.format(PACKAGE_STATEMENT,
                StringUtils.getCanonicalNameFromPath(outputDir));
    }

    @Override
    public String getImports() {
        return importDeclarations.stream().collect(Collectors.joining("\n"));
    }

    @Override
    public void importClass(String canonicalName) {
        String importStatement = String.format(IMPORT_STATEMENT, canonicalName);
        importDeclarations.add(importStatement);
    }
}
