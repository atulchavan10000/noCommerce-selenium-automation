package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    private static ConfigurationManager configManager;
    private static final Properties PROPERTIES = new Properties();

    private ConfigurationManager() throws IOException {
        InputStream inputStream = ConfigurationManager.class.getResourceAsStream("/envConfigs/default-config.properties");
        if(inputStream != null){
            PROPERTIES.load(inputStream);
        }else{
            System.out.println("config File not found at location");
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
}
