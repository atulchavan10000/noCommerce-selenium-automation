package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    private static ConfigurationManager configManager;
    private static final Properties PROPERTIES = new Properties();
    static Logger LOG = LoggerFactory.getLogger(ConfigurationManager.class);

    private static final String ENV = ConfigurationManager.getInstance().getProperty("env");
    private ConfigurationManager() throws IOException {
        InputStream inputStream = ConfigurationManager.class.getResourceAsStream("/envConfigs/default-config.properties");
        if(inputStream != null){
            PROPERTIES.load(inputStream);
            LOG.info("Configs to be loaded for environment: " + ENV);
        }else{
            LOG.error("config File not found at location");
        }
    }

    public static ConfigurationManager getInstance(){
        if(configManager == null){
            synchronized (ConfigurationManager.class){
                if (configManager == null){
                    try{
                        configManager = new ConfigurationManager();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return configManager;
    }

    public String getProperty(String name){
        return System.getProperty(name, PROPERTIES.getProperty(name));
    }

    public String getUrl(String name) {
        String key = ENV + "." + name;
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }
}
