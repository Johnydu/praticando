package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDB {
    public static Connection conectar() throws Exception {
        String url = getConfig("DB_URL", "db.url", "jdbc:mysql://localhost:3306/rh_duarte");
        String user = getConfig("DB_USER", "db.user", null);
        String password = getConfig("DB_PASSWORD", "db.password", null);

        if (user == null || password == null) {
            throw new IllegalStateException(
                    "Defina DB_USER e DB_PASSWORD (ou -Ddb.user e -Ddb.password) para conectar ao banco."
            );
        }

        return DriverManager.getConnection(url, user, password);
    }

    private static String getConfig(String envKey, String propertyKey, String defaultValue) {
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        String propertyValue = System.getProperty(propertyKey);
        if (propertyValue != null && !propertyValue.isBlank()) {
            return propertyValue;
        }

        return defaultValue;
    }
}
