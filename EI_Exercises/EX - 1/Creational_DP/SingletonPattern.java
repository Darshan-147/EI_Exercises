// Scenario: A global configuration manager that handles the application's settings.

class ConfigurationManager {
    private static ConfigurationManager instance;
    private String configValue;

    private ConfigurationManager() { }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public void setConfig(String configValue) {
        this.configValue = configValue;
    }

    public String getConfig() {
        return configValue;
    }
}

public class SingletonPattern {
    public static void main(String[] args) {
        ConfigurationManager config = ConfigurationManager.getInstance();
        config.setConfig("Reddish Theme");
        
        ConfigurationManager anotherConfig = ConfigurationManager.getInstance();
        System.out.println("Current Config: " + anotherConfig.getConfig());  
        // will give reddish theme as output
    }
}
