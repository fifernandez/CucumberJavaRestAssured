package support;

import org.json.simple.JSONObject;
import java.util.HashMap;

public class Endpoints {
    private static HashMap<String, HashMap<String, String>> endpoints;
    static public String getURI(String uriName) {
        return endpoints.get(uriName).get(Environment.getMode());
    }

    @SuppressWarnings("unchecked")
    public static void setEndpoints(JSONObject newUris) {
        endpoints = new HashMap<String, HashMap<String, String>>();
        for (String key : (Iterable<String>) newUris.keySet()) {
            JSONObject value = (JSONObject) newUris.get(key);
            HashMap<String, String> newEndpoint = new HashMap<String, String>();
            for (String key2 : (Iterable<String>) value.keySet()) {
                newEndpoint.put(key2, (String) value.get(key2));
            }
            endpoints.put(key, newEndpoint);
        }
    }

    public static boolean endpointDefined(String wanted) {
        return endpoints.containsKey(wanted);
    }
}
