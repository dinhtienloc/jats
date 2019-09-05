package vn.locdt.jats.module.shell.context;

import sun.rmi.runtime.Log;
import vn.locdt.jats.module.shell.exception.ContextNotFoundException;
import vn.locdt.jats.util.common.LogUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShellRuntimeContext {
    private static final Map<ContextKey, Object> context = new HashMap<>();
    private static final Properties contextProps = new Properties();
    private static final Map<EnviromentKey, Object> enviromentProps = new HashMap<>();

    public static void addContext(ContextKey key, Object value) {
    	if (value == null)
    		return;

        context.put(key, value);
	    contextProps.setProperty(key.getPropKey(), value.toString());
	    LogUtils.printDebugLog("Added " + key.getPropKey() + " value: " + value);
    }

    public static void addEnviromentProp(EnviromentKey key, Object value) {
	    if (value == null)
		    return;

	    enviromentProps.put(key, value);
    }

    public static Properties getContextProperties() {
    	return contextProps;
    }

	public static Map<EnviromentKey, Object> getEnviromentProperties() {
		return enviromentProps;
	}

    public static ContextKey getContextKey(String propKey) {return ContextKey.findByPropKey(propKey);}

	public static Map<ContextKey, Object> getContextMap() {return context;}
    public static <C> C getContext(ContextKey key, Class<C> type) throws ContextNotFoundException {
    	Object obj = context.get(key);
    	if (obj == null || "".equals(obj.toString())) {
    		throw new ContextNotFoundException("Please setup '" + key.getPropKey() + "' config by using 'config:add' command. The operation will be stopped.");
		}
        return type.cast(context.get(key));
    }

    public static String getContextAsProp(ContextKey key) {
    	return (String) contextProps.get(key.getPropKey());
    }

    public static Object getEnviromentProp(EnviromentKey key) {
	    return enviromentProps.get(key);
    }
}
