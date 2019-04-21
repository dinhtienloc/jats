package vn.locdt.jats.module.shell.config;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.util.common.LogUtils;

import java.util.Map;

@ShellComponent
public class ConfigListCommand {
	@ShellMethod(key = { "config:list"}, value = "Display all global configs")
	public String runCommand() {

		Map<ContextKey, Object> contextMap = ShellRuntimeContext.getContextMap();
		for (Map.Entry<ContextKey, Object> entry : contextMap.entrySet()) {
			Object value = entry.getValue();
			LogUtils.printLog("%s: %s", entry.getKey().getPropKey(), !(value instanceof String) ? "[Object]" : value);
		}

		return null;
	}
}
