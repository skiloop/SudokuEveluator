package com.shiloop.sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/4/11 10:03
 * Project: SudokuEveluator
 * Description:
 */
public class Note {

    /**
     * note set
     */
    Set<Integer> noteNumberSet = new HashSet<>();

    /**
     * note set capacity
     */
    private int capacity;

    public Note(int capacity) {
        for (int i = 1; i <= capacity; i++) {
            noteNumberSet.add(i);
        }
        this.capacity = capacity;
    }

    public boolean contain(int n) {
        return noteNumberSet.contains(n);
    }

    public int size() {
        return noteNumberSet.size();
    }

    public boolean add(int n) {
        if (n > 0 && n <= capacity) {
            return noteNumberSet.add(n);
        }
        return false;
    }

    public boolean remove(int n) {
        return noteNumberSet.remove(n);
    }

    public void clear() {
        noteNumberSet.clear();
    }

    public Set<Integer> getNotes() {
        return noteNumberSet;
    }
}
