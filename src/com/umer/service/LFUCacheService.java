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

}
