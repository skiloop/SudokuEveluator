package com.shiloop.sudoku;

import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/4/11 10:11
 * Project: SudokuEveluator
 * Description:
 */
public class Cell {
    /**
     * cell value
     */
    private int value;

    /**
     * cell note
     */
    private Note note;

    public Cell(int capacity) {
        value = 0;
        note = new Note(capacity);
    }

    public boolean setValue(int v) {
        if (note.contain(v)) {
            value = v;
            note.clear();
            return true;
        } else if (0 == v) {
            value = 0;
            return true;
        }
        return false;
    }

    public Set<Integer> getNotes() {
        return note.getNotes();
    }

    public void removeNote(int n) {
        note.remove(n);
    }

    public int getValue() {
        return value;
    }

    public boolean isEmpty() {
        return getValue() == 0;
    }
}
