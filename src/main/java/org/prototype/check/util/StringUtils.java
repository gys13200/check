package org.prototype.check.util;

/**
 * Created by root on 2017/3/31.
 */
public final class StringUtils {

    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
