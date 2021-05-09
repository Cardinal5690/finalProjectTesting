package model.dao.queriesManager;

import java.util.ResourceBundle;

public class QueriesResourceManager {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("queries");

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
