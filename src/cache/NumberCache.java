package cache;

import model.Number;
import org.json.JSONArray;
import web.WebConnect;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NumberCache {

    private static Logger logger = Logger.getLogger(NumberCache.class.getName());
    private static Map<Integer, List<Number>> map = new HashMap<>();

    private static boolean initStatus = false;
    private static NumberCache numberCache;

    private NumberCache() {

    }

    public static synchronized NumberCache getInstance() {
        if (numberCache == null)
            numberCache = new NumberCache();

        return numberCache;
    }

    public static void init() throws IOException {
        logger.log(Level.INFO, "\u001B[32m" + "Инициализация кеширования списка телефонов..." + "\u001B[0m");
        fillMap();
        initStatus = true;
        logger.log(Level.INFO, "\u001B[32m" + "Кеширование списка телефонов успешно завершено!" + "\u001B[0m");
    }

    private static void fillMap() throws IOException {
        Set<Integer> countries = CountryCache.getMap().keySet();
        for (Integer countryId : countries) {
            JSONArray jsonArray = WebConnect.getDataOfNumbers(countryId);
            List<Number> numbers = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Number number = new Number.NumberBuilder()
                        .setNumber(jsonArray.getJSONObject(i).get("number").toString())
                        .setCountry(jsonArray.getJSONObject(i).get("country").toString())
                        .setCountryText(jsonArray.getJSONObject(i).get("country_text").toString())
                        .setDataHumans(jsonArray.getJSONObject(i).get("data_humans").toString())
                        .setFullNumber(jsonArray.getJSONObject(i).get("full_number").toString())
                        .setMaxDate(jsonArray.getJSONObject(i).get("maxdate").toString())
                        .setUpdatedAt(jsonArray.getJSONObject(i).get("updated_at").toString())
                        .setStatus(jsonArray.getJSONObject(i).get("country").toString())
                        .build();
                numbers.add(number);
            }
            map.put(countryId, numbers);
        }
    }

    public static List<Number> getNumbersByCountryId(Integer countryId) {
        return map.get(countryId);
    }

    public static boolean isInit() {
        return initStatus;
    }

    public static void putToMap(Integer integer, List<Number> number) {
        map.put(integer, number);
    }
}
