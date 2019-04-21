package vn.locdt.jats.module.shell.context;

import sun.rmi.runtime.Log;
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

    public static <C> C getContext(String propKey, Class<C> type) {
    	ContextKey contextKey = getContextKey(propKey);
    	if (contextKey == null) {
    		return null;
	    }

	    return getContext(contextKey, type);
    }
	public static Map<ContextKey, Object> getContextMap() {return context;}
    public static <C> C getContext(ContextKey key, Class<C> type) {
        return type.cast(context.get(key));
    }

    public static String getContextAsProp(ContextKey key) {
    	return (String) contextProps.get(key.getPropKey());
    }

    public static Object getEnviromentProp(EnviromentKey key) {
	    return enviromentProps.get(key);
    }
}
