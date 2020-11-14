package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
    java.util.Properties properties = new java.util.Properties();
    private static Properties INSTANCE = null;
    File prop = new File("./src/main/resources/application.properties");

    public Properties() {
        try {
            properties.load(new FileInputStream(prop));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Properties();
        }
        return INSTANCE;
    }

    public java.util.Properties getProperties() {
        return properties;
    }
}
