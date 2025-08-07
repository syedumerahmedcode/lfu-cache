package com.umer;

import com.umer.service.LFUCacheService;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        int initialCapacity = 2;
        LFUCacheService lfuCache = new LFUCacheService(initialCapacity);

        lfuCache.put(1, 1); // cache is {1=1}
        lfuCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lfuCache.get(1)); // return 1
        lfuCache.put(3, 3); // evicts key 2, cache is {1=1, 3=3}
        System.out.println(lfuCache.get(2)); // return -1 (not found)
        lfuCache.put(4, 4); // evicts key 1, cache is {3=3, 4=4}
        System.out.println(lfuCache.get(1)); // return -1 (not found)
        System.out.println(lfuCache.get(3)); // return 3
        System.out.println(lfuCache.get(4)); // return 4
    }
}
