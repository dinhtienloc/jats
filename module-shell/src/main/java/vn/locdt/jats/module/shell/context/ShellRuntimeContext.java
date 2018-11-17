package vn.locdt.jats.module.shell.context;

import java.util.HashMap;
import java.util.Map;

public class ShellRuntimeContext {
    public static final String DATABASE_INFO = "DATABASE_INFO";

    private static final Map<String, Object> context = new HashMap<>();

    public static void  add(String key, Object value) {
        context.put(key, value);
    }

    public static Object get(String key) {
        return context.get(key);
    }
}
