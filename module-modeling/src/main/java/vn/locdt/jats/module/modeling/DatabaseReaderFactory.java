package vn.locdt.jats.module.modeling;


import vn.locdt.jats.module.modeling.exception.SystemReaderNotFoundException;
import vn.locdt.jats.module.modeling.system.SystemModeling;
import vn.locdt.jats.module.modeling.system.mysql.MySQLSystemModeling;
import vn.locdt.jats.module.modeling.system.postgres.PostgreSQLSystemModeling;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


/**
 * Created by locdt on 1/31/2018.
 */
public class DatabaseReaderFactory {
    private static Map<String, Callable<SystemModeling>> systems = new HashMap();

    static {
        systems.put(MySQLSystemModeling.SYSTEM, MySQLSystemModeling::new);
	    systems.put(PostgreSQLSystemModeling.SYSTEM, PostgreSQLSystemModeling::new);
    }

    public static SystemModeling createSystemReader(String databaseType, DatabaseMetadataWrapper wrapper) throws SystemReaderNotFoundException {
        try {
            SystemModeling modeling = systems.get(databaseType).call();
            modeling.setWrapper(wrapper);
            return modeling;
        } catch (Exception e) {
            throw new SystemReaderNotFoundException("Could not create reader for '" + databaseType + "' database", e);
        }
    }
}
