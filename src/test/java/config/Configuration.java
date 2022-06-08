package config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import support.BasePath;
import support.Endpoints;
import support.Environment;
import support.Users;
import util.*;

public class Configuration {
    static final String configFile = "src/test/java/config/config.json";

    public static boolean loadEnvironments() {
        JSONArray js = JsonReader.readJSONArrayFromFile(configFile, "environments");
        if (js != null) {
            Environment.setEnvs(js);
            return true;
        } else {
            return false;
        }
    }

    public static boolean loadModes() {
        JSONArray js = JsonReader.readJSONArrayFromFile(configFile, "modes");
        if (js != null) {
            Environment.setModes(js);
            return true;
        } else {
            return false;
        }
    }

    public static boolean loadDefaultEnvironmentAndMode() {
        JSONObject js = JsonReader.readFromFile(configFile);
        if (js != null) {
            String newDefaultEnv = (String) js.get("defaultEnvironment");
            String newDefaultMode = (String) js.get("defaultMode");
            Environment.setDefaultEnv(newDefaultEnv);
            Environment.setDefaultMode(newDefaultMode);
            return true;
        } else {
            return false;
        }
    }

    public static boolean loadBasePaths() {
        JSONObject js = JsonReader.readJSONObjectFromFile(configFile, "basePaths");
        if (js != null) {
            BasePath.setBasePaths(js);
            return true;
        } else {
            return false;
        }
    }

    public static boolean loadEndpoints() {
        JSONObject js = JsonReader.readJSONObjectFromFile(configFile, "endpoints");
        if (js != null) {
            Endpoints.setEndpoints(js);
            return true;
        } else {
            return false;
        }
    }

    public static boolean loadUsers() {
        JSONObject js = JsonReader.readJSONObjectFromFile(configFile, "users");
        if (js != null) {
            Users.setUsers(js);
            return true;
        } else {
            return false;
        }
    }

    public static void loadAllConfigs() {
        Assert.assertTrue("Failed to load environments values.", loadEnvironments());
        Assert.assertTrue("Failed to load modes values.", loadModes());
        Assert.assertTrue("Failed to load default environment value.", loadDefaultEnvironmentAndMode());
        Assert.assertTrue("Failed to load Base Path values.", loadBasePaths());
        Assert.assertTrue("Failed to load Endpoints values.", loadEndpoints());
        Assert.assertTrue("Failed to load Users values.", loadUsers());
    }

    public static void main(String[] args) {
        //loadAllConfigs();
        loadBasePaths();
        //loadModes();
    }

}
