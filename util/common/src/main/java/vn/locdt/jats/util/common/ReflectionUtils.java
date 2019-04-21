package vn.locdt.jats.util.common;

import org.reflections.Reflections;

import java.util.Set;

public class ReflectionUtils {
	public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type, String packagePath) {
		Reflections reflections = new Reflections(packagePath);
		return reflections.getSubTypesOf(type);
	}

	public static <T> T newInstanceFromClass(final Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
