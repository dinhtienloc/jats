package vn.locdt.jats.synergix.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.synergix.addon.db.DatabaseLoader;
import vn.locdt.jats.util.common.LogUtils;

@ShellComponent
public class ConnectDatabaseCommand {

    @ShellMethod(key = {"connect"}, value = "Create connection to database")
    public String runCommand(@ShellOption(value = {"-n", "--name"}) String dbName) {
        DatabaseInfo info = DatabaseLoader.getInfo(dbName);
        if (info == null) {
            return LogUtils.createErrorLog("Database '" + dbName + "' not found!");
        }

        ShellRuntimeContext.addContext(ContextKey.DATABASE_INFO, info);
        info.getConnection();


        return LogUtils.createSuccessLog("Connect successfully!");
    }

}
