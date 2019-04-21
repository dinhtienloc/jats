package vn.locdt.jats.module.generator.context;

import vn.locdt.jats.module.generator.FileType;
import vn.locdt.jats.util.common.FileUtils;

/**
 * Created by locdt on 2/9/2018.
 */
public abstract class GenerationContext<CM> {
    protected CM contextModel;
    protected String outputName;
    protected FileType fileType;
    protected String outputDirectory;

    public GenerationContext() {}

    public GenerationContext(FileType fileType) {
        this.fileType = fileType;
    }

    public GenerationContext(String outputDirectory, String outputName, FileType fileType) {
        this.outputDirectory = outputDirectory;
        this.outputName = outputName;
        this.fileType = fileType;
    }

    public GenerationContext(CM contextModel, String outputDirectory,
							 String outputName, FileType fileType) {
        this(outputDirectory ,outputName, fileType);
        this.contextModel = contextModel;
    }

    public CM getContextModel() {
        return this.contextModel;
    }

    public void setContextModel(CM contextModel) {
        this.contextModel = contextModel;
    }

    public String getOutputName() {
        return this.outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public FileType getFileType() {
        return this.fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

	public String getOutputDirectory() {
		return this.outputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public String getOutputPath() {
		return FileUtils.path(this.outputDirectory, this.outputName + this.fileType.getExt());
	}
}
