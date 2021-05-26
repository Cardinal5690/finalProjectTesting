package com.testing.dao.util;

import java.util.ResourceBundle;

public class QueriesResourceManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("queries");

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
