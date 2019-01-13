package by.mlionik.cafe.manager;

import java.util.ResourceBundle;

/**
 * The type Configuration manager.
 */
public class ConfigurationManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("prop.config");

    /**
     * Instantiates a new configuration manager.
     */
    private ConfigurationManager() {
    }

    /**
     * Gets the property from resource bundle.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
