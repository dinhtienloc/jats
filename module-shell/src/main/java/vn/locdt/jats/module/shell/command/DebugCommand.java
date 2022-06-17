package vn.locdt.jats.module.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import vn.locdt.jats.util.common.LogUtils;

@ShellComponent
public class DebugCommand extends QuestionCommand {
    @ShellMethod(key = {"debug"}, value = "On/Off debug log")
    public String runCommand(String status) {

        if ("on".equalsIgnoreCase(status)) {
            LogUtils.enableDebug();
        } else if ("off".equalsIgnoreCase(status)) {
            LogUtils.disableDebug();
        } else {
            LogUtils.printErrorLog("Unknown debug status");
        }
        return null;
    }
}
