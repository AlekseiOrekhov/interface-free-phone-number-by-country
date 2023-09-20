package cache;

import org.json.JSONArray;
import web.WebConnect;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryCache {

    private static Logger logger = Logger.getLogger(CountryCache.class.getName());
    private static Map<Integer, String> map = new HashMap<>();

    private static boolean initStatus = false;
    private static CountryCache countryCache;

    private CountryCache() {

    }

    public static synchronized CountryCache getInstance() {
        if (countryCache == null)
            countryCache = new CountryCache();

        return countryCache;
    }

    public static void init() throws IOException {
        logger.log(Level.INFO, "\u001B[32m" + "Инициализация кеширования списка стран..." + "\u001B[0m");
        fillMap(WebConnect.getDataOfCountries());
        initStatus = true;
        logger.log(Level.INFO, "\u001B[32m" + "Кеширование списка стран успешно завершено!" + "\u001B[0m");
    }

    private static void fillMap(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            map.put(Integer.valueOf(jsonArray.getJSONObject(i).get("country").toString()), jsonArray.getJSONObject(i).get("country_text").toString());
        }
    }

    public static Map<Integer, String> getMap() {
        return map;
    }

    public static boolean isInit() {
        return initStatus;
    }

    public static Integer getCountryIdByCountryText(String countryText) {
        if (map.containsValue(countryText)) {
            for (Integer key : map.keySet()) {
                if (map.get(key).equals(countryText)) {
                    return key;
                }
            }
        } else {
            logger.log(Level.WARNING, "Страны с именем " + countryText + " не нашлось!");
            return -1;
        }
        logger.log(Level.WARNING, "Страны с именем " + countryText + " не нашлось!");
        return -1;
    }


}
