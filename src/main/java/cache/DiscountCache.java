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
//    Make the constructor private (so no one can use the new keyword).
    private static class CacheHolder {
        private static final DiscountCache INSTANCE = new DiscountCache();
    }

    //Provide a public static getInstance() method that hands out the single shared copy.
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
