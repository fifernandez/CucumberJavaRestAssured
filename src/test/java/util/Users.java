package util;

import org.json.simple.JSONObject;

public class Users {
    private static JSONObject users = null;
    private static final String fileName = "src/test/java/util/users.json";

    static public String getUserEmail(String userName) {
        checkLoaded();
        return (String) ((JSONObject) users.get(userName)).get("email");
    }

    static public String getUserPassword(String userName) {
        checkLoaded();
        return (String) ((JSONObject) users.get(userName)).get("password");
    }

    private static void checkLoaded(){
        if (users == null) {
            users = Helpers.readFromFile(fileName);
        }
    }

    public static void main(String[] args) {
        System.out.println(getUserEmail("john"));
        System.out.println(getUserPassword("john"));
    }

}
