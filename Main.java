public class Main {
    public static void main(String[] args) {
        CacheManager cacheManager = new CacheManager();

        // Add cache levels
        cacheManager.addCacheLevel(3, "LRU"); // L1 with size 3 and LRU eviction policy
        cacheManager.addCacheLevel(2, "LFU"); // L2 with size 2 and LFU eviction policy

        // Insert data
        cacheManager.put("A", "1");
        cacheManager.put("B", "2");
        cacheManager.put("C", "3");

        // Retrieve data
        System.out.println("Get A: " + cacheManager.get("A")); // Should return 1

        // Insert more data and test eviction
        cacheManager.put("D", "4"); // L1 should evict the least recently used (B or C)
        System.out.println("Get C: " + cacheManager.get("C")); // Should fetch from L2 and promote to L1

        // Display the cache state
        cacheManager.displayCache();
    }
}
