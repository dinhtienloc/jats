package vn.locdt.jats.synergix.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.CollectionUtils;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.synergix.addon.db.DatabaseGroup;
import vn.locdt.jats.synergix.addon.db.DatabaseLoader;
import vn.locdt.jats.util.common.LogUtils;

import java.util.Set;

@ShellComponent
public class DatabaseListCommand extends QuestionCommand {

    @ShellMethod(key = {"dblist"}, value = "Create connection to database")
    public String runCommand() {

        Set<DatabaseGroup> groups = DatabaseLoader.getDbGroups();
        if (CollectionUtils.isEmpty(groups)) {
            return LogUtils.createSuccessLog("No database not found!");
        }

        for (DatabaseGroup group : groups) {
            System.out.println(group.toString());
        }
        return null;
    }
}
