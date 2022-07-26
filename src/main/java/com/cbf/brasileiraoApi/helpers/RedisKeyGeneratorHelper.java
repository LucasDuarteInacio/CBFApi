package com.cbf.brasileiraoApi.helpers;

import org.springframework.util.StringUtils;

import java.util.Objects;

public class RedisKeyGeneratorHelper {
    private static final String SEPARATOR = "_";

    private RedisKeyGeneratorHelper() {}

    public static String keyGenerator(
            final String className, final String methodName, final Object... params) {
        final StringBuilder key = new StringBuilder();
        key.append(className);
        key.append(SEPARATOR);
        key.append(methodName);

        for (final Object param : params) {
            if (Objects.isNull(param) || !param.getClass().isArray()) {
                key.append(SEPARATOR);
                key.append(param);
            } else {
                key.append(SEPARATOR);
                key.append(StringUtils.arrayToDelimitedString((Object[]) param, SEPARATOR));
            }
        }
        return key.toString();
    }
}