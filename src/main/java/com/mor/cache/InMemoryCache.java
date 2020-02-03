package com.mor.cache;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.LRUMap;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class InMemoryCache<K, T> {

    private final LRUMap<K, CacheObject> cacheMap;
    private long timeToLive;

    public InMemoryCache(long ttlInSeconds, final long cleanupTimeInterval, int maxItems) {
        this.timeToLive = ttlInSeconds * 1000;

        cacheMap = new LRUMap<>(maxItems);

        if (timeToLive > 0 && cleanupTimeInterval > 0) {

            Timer cleanUpTimer = new Timer(true);
            TimerTask cleanupTask = new TimerTask() {
                @Override
                public void run() {
                    cleanup();
                }
            };

            cleanUpTimer.scheduleAtFixedRate(cleanupTask, 0, cleanupTimeInterval * 1000);
        }
    }

    public void put(K key, T value) {
        synchronized (cacheMap) {
            cacheMap.put(key, new CacheObject(value));
        }
    }

    public T get(K key) {
        synchronized (cacheMap) {
            CacheObject c = cacheMap.get(key);

            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }

    public void remove(K key) {
        synchronized (cacheMap) {
            cacheMap.remove(key);
        }
    }

    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }

    public void cleanup() {

        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey;

        synchronized (cacheMap) {
            MapIterator<K, CacheObject> itr = cacheMap.mapIterator();

            deleteKey = new ArrayList<>((cacheMap.size() / 2) + 1);
            K key;
            CacheObject c;

            while (itr.hasNext()) {
                key = itr.next();
                c = itr.getValue();

                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }
        for (K key : deleteKey) {
            synchronized (cacheMap) {
                cacheMap.remove(key);
            }

            Thread.yield();
        }
    }

    class CacheObject {
        long lastAccessed = System.currentTimeMillis();
        T value;

        CacheObject(T value) {
            this.value = value;
        }
    }
}

