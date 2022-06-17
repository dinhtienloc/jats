package vn.locdt.jats.module.shell.util;

import vn.locdt.jats.util.common.LogUtils;

public class ParamAssert {
    public static void isNull(Object data, String label) {
        if (data == null) {
            throw new IllegalArgumentException(LogUtils.createErrorLog(label + " must not be null"));
        }
    }

    public static void isEmpty(Object data, String label) {
        if (data == null || "".equals(data.toString())) {
            throw new IllegalArgumentException(LogUtils.createErrorLog(label + " must not be empty"));
        }
    }
}
