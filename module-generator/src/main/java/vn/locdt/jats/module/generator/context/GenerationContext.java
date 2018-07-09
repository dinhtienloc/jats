package vn.locdt.jats.module.generator.context;

import vn.locdt.jats.module.generator.FileType;

/**
 * Created by locdt on 2/9/2018.
 */
public abstract class GenerationContext<CM> {
    protected CM contextModel;
    protected String rootPackage;
    protected String projectPath;
    protected String outputName;
    protected FileType fileType;

    public GenerationContext() {}

    public GenerationContext(FileType fileType) {
        this.fileType = fileType;
    }

    public GenerationContext(String rootPackage, String projectPath, String outputName, FileType fileType) {
        this.rootPackage = rootPackage;
        this.projectPath = projectPath;
        this.outputName = outputName;
        this.fileType = fileType;
    }

    public GenerationContext(CM contextModel, String rootPackage, String projectPath,
							 String outputName, FileType fileType) {
        this(rootPackage, projectPath ,outputName, fileType);
        this.contextModel = contextModel;
    }

    public CM getContextModel() {
        return contextModel;
    }

    public void setContextModel(CM contextModel) {
        this.contextModel = contextModel;
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
