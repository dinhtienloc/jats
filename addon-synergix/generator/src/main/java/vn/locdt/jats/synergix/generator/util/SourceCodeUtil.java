package vn.locdt.jats.synergix.generator.util;

public class SourceCodeUtil {
    public String createClassWithTypeParameter(String className, String type) {
        return className + "<" + type + ">";
    }
}
