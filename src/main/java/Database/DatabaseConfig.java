package Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final String DATABASE_PATH = "/database_";
    private static final String DATABASE_EXTENSHION = ".properties";

    private final Properties properties;

    public DatabaseConfig(String choice)  {
        properties = new Properties();
        try {
            InputStream input = getClass().getResourceAsStream(DATABASE_PATH + choice + DATABASE_EXTENSHION);
            properties.load(input);
        }catch (IOException ex){
            System.err.println("Не могу открыть файл для подключения к базе данных");

        }

    }

    public String getDatabaseUrl() {
        return properties.getProperty("database.url");
    }

    public String getDatabaseUsername() {
        return properties.getProperty("database.username");
    }

    public String getDatabasePassword() {
        return properties.getProperty("database.password");
    }
}
