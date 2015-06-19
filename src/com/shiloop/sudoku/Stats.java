package com.shiloop.sudoku;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/6/20 5:42
 * Project: SudokuFetcher
 * Description:
 */
public class Stats<T> {
    private Map<T, Integer> map;

    public Stats(Set<T> keySet) {
        map = new HashMap<>();
        if (null != keySet) {
            for (T obj : keySet) {
                map.put(obj, 0);
            }
        }
    }

    public void increase(T key) {
        if (map.containsKey(key)) {
            map.replace(key, map.get(key) + 1);
        }
    }

    public void increase(Collection<T> keys) {
        if (null == keys || keys.isEmpty()) return;
        for (T obj : keys) {
            increase(obj);
        }
    }

    public int get(T key) {
        if (map.containsKey(key))
            return map.get(key);
        else return 0;
    }

    public void reset() {
        for (T key : map.keySet()) {
            map.replace(key, 0);
        }
    }

    public T findKey(int value) {
        for (T key : map.keySet()) {
            if (map.get(key) == value) return key;
        }
        return null;
    }
}
