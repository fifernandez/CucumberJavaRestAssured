package util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class Helpers {
    @SuppressWarnings("unchecked")
    public static JSONObject readFromFile(String fileName) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            Object obj = jsonParser.parse(reader);
            return (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
