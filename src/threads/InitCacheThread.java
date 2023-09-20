package threads;

import cache.CountryCache;
import cache.NumberCache;
import web.WebConnect;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InitCacheThread extends Thread {
    private static final Logger logger = Logger.getLogger(InitCacheThread.class.getName());

    public void run() {
        try {
            initCache();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void initCache() throws Exception {
        try {
            if (WebConnect.pingCountries()) {
                CountryCache.init();
                NumberCache.init();
            }
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Нет доступа к сети!");
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
