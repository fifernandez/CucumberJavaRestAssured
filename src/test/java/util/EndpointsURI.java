package util;

import org.json.simple.JSONObject;

public class EndpointsURI {
    private static JSONObject uris = null;
    private static final String fileName = "src/test/java/util/uris.json";

    static public String getURI(String uriName) {
        checkLoaded();
        return (String) ((JSONObject) uris.get(uriName)).get("public");
    }

    private static void checkLoaded(){
        if (uris == null) {
            uris = Helpers.readFromFile(fileName);
        }
    }

    public static boolean endpointDefined(String wanted) {
        return uris.containsKey(wanted);
    }

    public static void main(String[] args) {
        System.out.println(getURI("todos"));
    }

}
