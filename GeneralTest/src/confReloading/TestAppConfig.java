package confReloading;

public class TestAppConfig {

    public static void main(String args[]) {
        AppConfiguration appConfig = AppConfiguration.getInstance();

        System.out.println(appConfig.getString("app-name"));

        // Sleep for 30 seconds, modify the config file during this time
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(appConfig.getString("app-name"));


    }
}
