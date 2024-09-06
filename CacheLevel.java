import java.util.*;

class CacheLevel {
    private int size;
    private String evictionPolicy;
    private Map<String, String> cache;
    private LinkedHashMap<String, Integer> accessFrequency; // For LFU

    // Constructor
    public CacheLevel(int size, String evictionPolicy) {
        this.size = size;
        this.evictionPolicy = evictionPolicy;
        this.cache = new LinkedHashMap<>(size, 0.75f, evictionPolicy.equals("LRU"));
        this.accessFrequency = new LinkedHashMap<>();
    }

    public String get(String key) {
        if (!cache.containsKey(key)) return null;

        // Update access frequency for LFU
        if (evictionPolicy.equals("LFU")) {
            accessFrequency.put(key, accessFrequency.getOrDefault(key, 0) + 1);
        }
        return cache.get(key);
    }

    public void put(String key, String value) {
        if (cache.size() >= size) {
            evict();
        }
        cache.put(key, value);

        if (evictionPolicy.equals("LFU")) {
            accessFrequency.put(key, accessFrequency.getOrDefault(key, 0) + 1);
        }
    }

    private void evict() {
        String evictKey = null;
        if (evictionPolicy.equals("LRU")) {
            evictKey = cache.keySet().iterator().next(); // Get the first item (least recently used)
        } else if (evictionPolicy.equals("LFU")) {
            evictKey = accessFrequency.entrySet().stream()
                                      .min(Map.Entry.comparingByValue())
                                      .get().getKey(); // Get the least frequently used key
        }
        if (evictKey != null) {
            cache.remove(evictKey);
            accessFrequency.remove(evictKey);
        }
    }

    public Map<String, String> getCache() {
        return cache;
    }
}
