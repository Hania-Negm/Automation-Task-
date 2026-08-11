package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataDriven {

    public JSONObject jsonReader(String section) throws IOException, ParseException {

        // Object responsible for parsing the JSON file
        JSONParser parser = new JSONParser();

        // Read the JSON file
        Object object = parser.parse(
                new FileReader("src/test/resources/testData.json")
        );

        // Convert Object to JSONObject
        JSONObject jsonObject = (JSONObject) object;

        // Return the required section
        return (JSONObject) jsonObject.get(section);
    }

    public List<String> getCartProducts() throws IOException, ParseException {

        // Object responsible for parsing the JSON file
        JSONParser parser = new JSONParser();

        // Read the JSON file
        Object object = parser.parse(
                new FileReader("src/test/resources/testData.json")
        );

        // Convert Object to JSONObject
        JSONObject jsonObject = (JSONObject) object;

        // Get cartProducts as JSONArray
        JSONArray cartProducts = (JSONArray) jsonObject.get("cartProducts");

        // Convert JSONArray to List<String>
        List<String> products = new ArrayList<>();

        for (Object product : cartProducts) {
            products.add(product.toString());
        }

        return products;
    }
}