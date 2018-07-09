package vn.locdt.jats.util.intellij.execute;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IntelliJProjectEntryGenerator {
    public static void generateEntry() {
        Path sourcePath = Paths.get("ipr.exe");
        System.out.println(Files.exists(sourcePath));
    }
}
