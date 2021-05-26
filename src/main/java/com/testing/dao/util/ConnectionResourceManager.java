package com.testing.dao.util;

import java.util.ResourceBundle;

public class ConnectionResourceManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("connection");

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
