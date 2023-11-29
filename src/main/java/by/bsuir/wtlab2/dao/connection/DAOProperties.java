package by.bsuir.wtlab2.dao.connection;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.exception.DAOConfigurationException;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
@Singleton
public final class DAOProperties {

    public static final String FILE_NAME = "database.properties";
    public static final String URL = "db.url";
    public static final String DRIVER = "db.driver";
    public static final String USER = "db.user";
    public static final String PASSWORD = "db.password";
    public static final String POOL_SIZE = "db.pool.initialSize";

    private final Properties properties = new Properties();

    public DAOProperties() throws DAOConfigurationException {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(FILE_NAME));
        } catch (Exception e) {
            log.error("Failed to load properties", e);
            throw new DAOConfigurationException("Failed to load properties", e);
        }
    }

    public String getDriver() throws DAOConfigurationException {
        return getProperty(DRIVER);
    }

    public String getUrl() throws DAOConfigurationException {
        return getProperty(URL);
    }

    public String getUser() throws DAOConfigurationException {
        return getProperty(USER);
    }

    public String getPassword() throws DAOConfigurationException {
        return getProperty(PASSWORD);
    }

    public int getPoolSize() throws DAOConfigurationException {
        String property = getProperty(POOL_SIZE);

        try {
            return Integer.parseInt(property);
        } catch (NumberFormatException e) {
            log.error("Failed to parse pool size", e);
            throw new DAOConfigurationException("Failed to parse pool size", e);
        }
    }

    private String getProperty(String key) throws DAOConfigurationException {
        String property = properties.getProperty(key);

        if (property == null || property.isEmpty()) {
            log.error("Property not found");
            throw new DAOConfigurationException("Property not found");
        }

        return property;
    }
}
