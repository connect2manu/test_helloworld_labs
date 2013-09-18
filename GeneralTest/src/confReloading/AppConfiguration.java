package confReloading;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

 public class AppConfiguration extends XMLConfiguration {

//    private static Log log = LogFactory.getLog(AppConfiguration.class);
    private static AppConfiguration instance;
    private static String configFile = "plugin.xml";

    // Singleton initialiser
    static {
        instance = new AppConfiguration(configFile);
    }

    /**
     * Constructor
     *
     * @param fileName Configuration file name.
     */
    private AppConfiguration(String fileName) {
        init(fileName);
        FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
        // You can set the refresh relay, default to 5 seconds
        //strategy.setRefreshDelay(100000);
        setReloadingStrategy(strategy);
    }

    /**
     * Initialize the class.
     *
     * @param fileName Configuration file name.
     */
    private void init(String fileName) {
        setFileName(fileName);
        try {
            load();
        } catch (ConfigurationException configEx) {
			// log.error(configEx.getMessage());
            configEx.printStackTrace();
        }
    }

    /**
     * Singleton access method.
     *
     * @return  Singleton
     */
    public static AppConfiguration getInstance() {
        return instance;
    }

}


