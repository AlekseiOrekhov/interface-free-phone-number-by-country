package web;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class WebConnect {

    WebConnect() {

    }

    private static final String urlCountries = "https://onlinesim.ru/api/getFreeCountryList";
    private static final String urlNumbers = "https://onlinesim.ru/api/getFreePhoneList?country=";

    public static boolean pingCountries() throws Exception {
        try {
            URL urlObj = new URL(urlCountries);

            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException();
            }

            conn.disconnect();
            return true;
        } catch (RuntimeException | UnknownHostException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public static JSONArray getDataOfCountries() throws IOException {
        JSONObject obj = getJSONObjectFromUrl(urlCountries);
        return obj.getJSONArray("countries");
    }

    private static JSONObject getJSONObjectFromUrl(String url) throws IOException {
        URL urlObj = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException();
        }

        String inline = "";
        Scanner scanner = new Scanner(urlObj.openStream());

        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }

        //Close the scanner
        scanner.close();
        conn.disconnect();

        //Using the JSON simple library parse the string into a json object
        return new JSONObject(inline);
    }

    public static JSONArray getDataOfNumbers(Integer s) throws IOException {
        JSONObject obj = getJSONObjectFromUrl(urlNumbers + s);
        return obj.getJSONArray("numbers");
    }
}
