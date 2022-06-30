package support;

import org.json.simple.JSONObject;
import java.util.HashMap;

public class Users {

    private static HashMap<String, HashMap<String, String>> users;

    static public String getUserEmail(String userName) {
        return users.get(userName).get("email");
    }

    static public String getUserPassword(String userName) {
        return users.get(userName).get("password");
    }

    @SuppressWarnings("unchecked")
    public static void setUsers(JSONObject newUris) {
        users = new HashMap<String, HashMap<String, String>>();
        for (String key : (Iterable<String>) newUris.keySet()) {
            JSONObject value = (JSONObject) newUris.get(key);
            HashMap<String, String> newUser = new HashMap<String, String>();
            for (String key2 : (Iterable<String>) value.keySet()) {
                newUser.put(key2, (String) value.get(key2));
            }
            users.put(key, newUser);
        }
    }

}
