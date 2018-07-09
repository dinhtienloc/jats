package vn.locdt.jats.module.generator;

/**
 * Created by locdt on 2/9/2018.
 */
public enum FileType {
    JAVA(".java");
    private String ext;
    FileType(String ext) {this.ext = ext;}

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
