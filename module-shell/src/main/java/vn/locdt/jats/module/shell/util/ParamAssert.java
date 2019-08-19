package vn.locdt.jats.module.shell.util;

import vn.locdt.jats.util.common.LogUtils;

public class ParamAssert {
	public static boolean notNull(Object data, String label) {
		if (data == null) {
			LogUtils.printErrorLog(label + " must be not null");
			return false;
		}
		return true;
	}
}
