package cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiscountCache {
    private final Map<String, Double> cache;

    public DiscountCache() {
        System.out.println("--> [SYSTEM] Initializing Discount Cache... Loading codes from database into memory.");
        cache = new ConcurrentHashMap<>();
        // Simulating a one-time database load of valid promo codes
        cache.put("SAVE20", 0.20); // 20% off
        cache.put("WINTER50", 0.50); // 50% off
    }

    //singleton, single instance only everywhere
    private static class CacheHolder {
        private static final DiscountCache INSTANCE = new DiscountCache();
    }

    public static DiscountCache getInstance() {
        return CacheHolder.INSTANCE;
    }

    public double getDiscountMultiplier(String promoCode){
        if(promoCode == null){
            return 0.0;
        }
        return cache.getOrDefault(promoCode.toUpperCase(), 0.0);
    }
}
