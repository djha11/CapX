import java.util.*;

class CacheManager {
    private List<CacheLevel> cacheLevels;

    public CacheManager() {
        cacheLevels = new ArrayList<>();
    }

    // Add a new cache level
    public void addCacheLevel(int size, String evictionPolicy) {
        cacheLevels.add(new CacheLevel(size, evictionPolicy));
    }

    // Remove a cache level
    public void removeCacheLevel(int level) {
        if (level >= 0 && level < cacheLevels.size()) {
            cacheLevels.remove(level);
        }
    }

    // Get data from the cache
    public String get(String key) {
        for (int i = 0; i < cacheLevels.size(); i++) {
            String value = cacheLevels.get(i).get(key);
            if (value != null) {
                // If found, promote to higher levels
                promoteToHigherLevels(key, value, i);
                return value;
            }
        }
        return null; // Cache miss
    }

    // Insert data into L1 cache
    public void put(String key, String value) {
        if (cacheLevels.size() > 0) {
            cacheLevels.get(0).put(key, value); // Always insert into L1 cache
        }
    }

    // Promote data to higher levels
    private void promoteToHigherLevels(String key, String value, int foundLevel) {
        for (int i = foundLevel; i > 0; i--) {
            cacheLevels.get(i).getCache().remove(key); // Remove from current level
            cacheLevels.get(i - 1).put(key, value); // Move to higher level
        }
    }

    // Display the current state of all cache levels
    public void displayCache() {
        for (int i = 0; i < cacheLevels.size(); i++) {
            System.out.println("Cache Level " + (i + 1) + ": " + cacheLevels.get(i).getCache());
        }
    }
}
