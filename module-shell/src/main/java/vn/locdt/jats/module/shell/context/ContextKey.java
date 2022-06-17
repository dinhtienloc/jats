package vn.locdt.jats.module.shell.context;

import java.util.HashMap;
import java.util.Map;

public enum ContextKey {
    DB_CONFIG("dbConfig"),
    DATABASE_INFO("databaseInfo"),
    GOBY_CONFIG_PATH("gobyConfigPath"),
    JDBC_URL("jdbcUrl"),
    TEMPLATE_PATH("templatePath"),
    PLUGIN_PATH("pluginPath"),
    VELOCITY_PATH("velocityPath"),
    TH6_PATH("th6Path"),
    SUPERMODEL_PATH("supermodelPath");


    private String propKey;
    private static final Map<String, ContextKey> map = new HashMap<>();

    static {
        for (ContextKey contextKey : ContextKey.values()) {
            map.put(contextKey.getPropKey(), contextKey);
        }
    }

    public static ContextKey findByPropKey(String propKey) {
        return map.get(propKey);
    }

    ContextKey(String propKey) {
        this.propKey = propKey;
    }

    public String getPropKey() {
        return this.propKey;
    }
}
