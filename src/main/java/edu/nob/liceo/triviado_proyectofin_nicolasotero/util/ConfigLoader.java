package edu.nob.liceo.triviado_proyectofin_nicolasotero.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigLoader.class.getResourceAsStream("/config.properties")) {
            if (is != null) {
                props.load(is);
            } else {
                System.err.println("No se encontró config.properties");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) { return props.getProperty(key); }
    public static int getInt(String key) { return Integer.parseInt(props.getProperty(key)); }
}
