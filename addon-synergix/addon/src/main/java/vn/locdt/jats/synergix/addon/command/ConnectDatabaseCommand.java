package vn.locdt.jats.synergix.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.synergix.addon.db.DatabaseLoader;
import vn.locdt.jats.util.common.LogUtils;

import java.sql.Connection;

@ShellComponent
public class ConnectDatabaseCommand {
    private static final String RAW_CONNECTION_TYPE = "raw";
    private static final String HIBERATE_CONNECTION_TYPE = "hbn";

    @ShellMethod(key = { "connect"}, value = "Create connection to database")
    public String runCommand(
//        @ShellOption(value = {"-t", "--type"}, defaultValue = "raw") String connectionType,
        @ShellOption(value = {"-n", "--name"}) String dbName) {

//        switch (connectionType) {
//            case RAW_CONNECTION_TYPE:
                DatabaseInfo info = DatabaseLoader.getInfo(dbName);
                if (info == null) {
                    return LogUtils.createErrorLog("Database '" + dbName + "' not found!");
                }

                ShellRuntimeContext.addContext(ContextKey.DATABASE_INFO, info);
                info.getConnection();
//                break;
//            case HIBERATE_CONNECTION_TYPE:
//                break;
//            default:
//                return LogUtils.createErrorLog("Invalid connection type '" + connectionType + "'");
//        }

        return LogUtils.createSuccessLog("Connect successfully!");
    }

}
