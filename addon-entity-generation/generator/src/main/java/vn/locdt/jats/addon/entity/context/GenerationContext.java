package vn.locdt.jats.addon.entity.context;

import vn.locdt.jats.addon.entity.FileType;
import vn.locdt.jats.addon.entity.modeling.DatabaseMetadataWrapper;

/**
 * Created by locdt on 2/9/2018.
 */
public abstract class GenerationContext {
    protected String outputDir;
    protected String outputName;
    protected FileType fileType;
    protected DatabaseMetadataWrapper wrapper;

    public GenerationContext() {}

    public GenerationContext(FileType fileType) {
        this.fileType = fileType;
    }

    public GenerationContext(String outputDir, String outputName, FileType fileType) {
        this.outputDir = outputDir;
        this.outputName = outputName;
        this.fileType = fileType;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
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

    public DatabaseMetadataWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(DatabaseMetadataWrapper wrapper) {
        this.wrapper = wrapper;
    }
}
