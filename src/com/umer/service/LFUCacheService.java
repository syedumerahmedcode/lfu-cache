package com.umer.service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The lfu cache is created using the following data structures:
 * - HashMap: To store the key value pairs
 * - HashMap: To store the frequency of every key
 * - TreeMap: To maintain the keys based on their keys for eviction.
 */
public class LFUCacheService {

    /**
     * This is set one time and then the capacty is fixed. Hence, final is used.
     */
    private final int capacity;
    private int minFrequency;
    /**
     * Used for storing key value pairs.
     */
    private final Map<Integer, Integer> values;
    /**
     * Used for storing frequency of each key.
     */
    private final Map<Integer, Integer> frequencies;

    /**
     * frequency -> (key -> value)
     */
    private final Map<Integer, TreeMap<Integer, Integer>> frequencyMap;

    public LFUCacheService(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        this.values = new HashMap<>();
        this.frequencies = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    public int get(int key) {
        // first check if the key even exist or not? If not return -1
        if (!values.containsKey(key)) {
            return -1;
        }
        // Update frequency
        // TODO: Better naming is required than frequency.
        int frequency = frequencies.get(key);
        frequencies.put(key, frequency + 1);
        frequencyMap.get(frequency).remove(key);

        // Update minFrequency
        if (frequencyMap.get(frequency).isEmpty()) {
            frequencyMap.remove(frequency);
            if (minFrequency == frequency) {
                minFrequency++;
            }
        }

        // Add to the next frequency
        // TODO: Please research more about computeIfAbsent()
        frequencyMap.computeIfAbsent(frequency + 1, k -> new TreeMap<>()).put(key, key);
        return values.get(key);
    }

    public void put(int key, int value) {
        // There should be some initial capacity defined.
        if (capacity <= 0) {
            return;
        }

        // Use Case: If the key is already existing...
        if (values.containsKey(key)) {
            values.put(key, value);
            // The following get() method call helps in updating the frequency
            get(key);
            return;
        }

        // Check if we need to evict based on capacity
        if (values.size() >= capacity) {
            // Yes, please evict the least frequently used key
            int evictKey = frequencyMap.get(minFrequency).firstKey();
            // We remove evictKey from everywhere(i.e. all data structures).
            frequencyMap.get(minFrequency).remove(evictKey);
            values.remove(evictKey);
            frequencies.remove(evictKey);
            // TODO: Please resrach more about this.
            if (frequencyMap.get(minFrequency).isEmpty()) {
                frequencyMap.remove(minFrequency);
            }
        }

        // Use case: If we are inserting a new key in the cache
        values.put(key, value);
        // This helps in setting up the initial frequency of the key provided in the
        // method.
        frequencies.put(key, 1);
        // TODO: Please resrach more about this.
        minFrequency = 1;
        frequencyMap.computeIfAbsent(1, k -> new TreeMap<>()).put(key, value);
    }

}
