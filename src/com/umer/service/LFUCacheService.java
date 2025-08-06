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
    private final Map<Integer, Integer> frequency;

    /**
     * frequency -> (key -> value)
     */
    private final Map<Integer, TreeMap<Integer, Integer>> frequencyMap;

    public LFUCacheService(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        this.values = new HashMap<>();
        this.frequency = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    public int get(int key) {
        return -1;
    }

}
