package com.githup.zzwloves.util;

/**
 * 字符串工具类
 * @author zhuzw
 * @version <b>1.0.0</b>
 */
public final class StringUtils {

    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断参数中是否有参数为空
     *
     * @param srcs 需要判断的字符串可变数组
     * @return 如果参数中至少有一个参数为null或“”，返回true，否则返回false
     */
    public static boolean isEmptyAny(final String... srcs) {
        for (String str : srcs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

}

