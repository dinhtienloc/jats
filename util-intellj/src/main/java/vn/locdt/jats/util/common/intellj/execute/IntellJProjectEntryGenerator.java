package vn.locdt.jats.util.common.intellj.execute;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IntellJProjectEntryGenerator {
    public static void generateEntry() {
        Path sourcePath = Paths.get("ipr.exe");
        System.out.println(Files.exists(sourcePath));
    }
}
