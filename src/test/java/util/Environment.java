package util;

import java.util.HashMap;

public class Environment {
    private static final String defaultEnv = "prod";
    private static String selected = null;

    private static final HashMap<String, String> environments = new HashMap<String, String>() {{
        //put("dev", "");
        //put("qa", "");
        //put("stage", "");
        put("prod", "https://jsonplaceholder.typicode.com");
    }};

    public static String getEnvironment(){
        if (selected == null) {
            String consoleEnv = System.getProperty("env");
            if (consoleEnv == null) {
                System.out.println(String.format("Environment not sent. Going to use default: %s", defaultEnv));
                selected = defaultEnv;
            }
            else if (!envIsAvailable(consoleEnv)) {
                System.out.println(String.format("Environment sent in parameter is not available. Sent: %s", consoleEnv));
                System.out.println(String.format("Going to use default: %s", defaultEnv));
                selected = defaultEnv;
            } else {
                System.out.println(String.format("Selected env: %s", consoleEnv));
                selected = consoleEnv;
            }
        }
        return selected;
    }

    public static String getBasePath(){
        return environments.get(getEnvironment());
    }

    public static boolean envIsAvailable(String desireEnv){
        return environments.containsKey(desireEnv);
    }

    public static void main(String[] args) {

    }

}
