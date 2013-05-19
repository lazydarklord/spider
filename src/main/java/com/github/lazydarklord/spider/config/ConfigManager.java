package com.github.lazydarklord.spider.config;

import java.util.Properties;

/**
 * Config Manager holds the configuration for the project. Implements singleton pattern.
 */
public class ConfigManager
{

    /** Singleton instance. */
    private static ConfigManager instance;

    /** Dictionary of all the configs. */
    Properties properties = new Properties();

    /**
     * Instantiates a new config manager.
     */
    private ConfigManager()
    {
        // try
        // {
        // properties.load(new FileInputStream("./src/main/resources/spec.txt"));
        // }
        // catch (IOException e)
        // {
        // System.out.println("Could not load config" + System.getProperty("user.dir"));
        // }

        // TODO: move this line into catch block
        initHardCodedConfigs();
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
        this.properties.setProperty("default-delim", ","); // default delimiter
        this.properties.setProperty("input-calc-delim", "-"); // delimiter for calculate distance input
        this.properties.setProperty("input-trips-delim", " to,"); // delimiter for all trips input
        this.properties.setProperty("input-shortest-delim", " to,"); // delimiter for shortest route input
        this.properties.setProperty("default-routes",
            "AB12,AD19,AE20,AG16,BC5,BD13,BI15,CD5,DE7,EF5,FA5,GF11,HA4,HB19,HG6,IJ10,IH21,JB7,JC15"); // default routes
        this.properties.setProperty("default-routes-short", "AB1,AC1,CB1,BD1"); // default routes for a short graph
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
