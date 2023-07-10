package com.cognixus.utils;

/**
 *
 * @author sinwei
 */
public class CommonUtils {
    public static boolean isStringNullOrEmpty(String input) {
        if (input == null || "".equals(input)) {
            return true;
        }
        return false;
    }
}
