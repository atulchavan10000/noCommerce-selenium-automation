package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;

public class DataManager {
    private static final Properties PROPERTIES = new Properties();
    static Logger LOG = LoggerFactory.getLogger(ConfigurationManager.class);


    private static final String ENV = ConfigurationManager.getInstance().getProperty("env");

    private DataManager() throws IOException {
        InputStream inputStream = ConfigurationManager.class.getResourceAsStream("/testData/env-test-data.properties");
        if(inputStream != null){
            PROPERTIES.load(inputStream);
            LOG.info("Data to be loaded for environment: " + ENV);
        }else{
            LOG.error("env test data File not found at location");
        }
    }

    private static DataManager manager;

    public static DataManager getInstance() {

        if (manager == null) {
            synchronized (ConfigurationManager.class) {
                if (manager == null) {
                    try {
                        manager = new DataManager();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return manager;
    }

    public String getString(String name) {
        String key = ENV + "." + name;
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }
}
