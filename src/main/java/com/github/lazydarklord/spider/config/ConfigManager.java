package com.github.lazydarklord.spider.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Config Manager holds the configuration for the project. Implements singleton pattern.
 */
public class ConfigManager
{

    /** Singleton instance. */
    private static ConfigManager instance;

    Properties properties = new Properties();

    /** Dictionary of all the configs. */
    private HashMap<String, String> config;

    /**
     * Instantiates a new config manager.
     */
    private ConfigManager()
    {
        config = new HashMap<String, String>();

        try
        {
            properties.load(new FileInputStream("./src/main/resources/spec.txt"));
        }
        catch (IOException e)
        {
            System.out.println("Could not load config"+System.getProperty("user.dir"));
            initHardCodedConfigs();
        }
    }

    /**
     * Gets the single instance of ConfigManager.
     * 
     * @return single instance of ConfigManager
     */
    public static ConfigManager getInstance()
    {
        if (instance == null)
        {
            instance = new ConfigManager();
        }
        return instance;
    }

    /**
     * Inits the configs.
     */
    private void initHardCodedConfigs()
    {
        config.put("default-delim", ","); // default delimiter
        config.put("input-calc-delim", "-"); // delimiter for calculate distance input
        config.put("input-trips-delim", " to,"); // delimiter for all trips input
        config.put("input-shortest-delim", " to,"); // delimiter for shortest route input
        config.put("default-routes",
            "AB12,AD19,AE20,AG16,BC5,BD13,BI15,CD5,DE7,EF5,FA5,GF11,HA4,HB19,HG6,IJ10,IH21,JB7,JC15"); // default routes
    }

    /**
     * Gets the configuration based on the key.
     * 
     * @param key configuration key
     * @return the configuration for a valid key, null otherwise
     */
    public String getConfig(String key)
    {
        return this.properties.getProperty(key);
    }

}
