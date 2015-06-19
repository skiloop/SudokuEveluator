package com.shiloop.sudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/4/11 10:02
 * Project: SudokuEveluator
 * Description:
 */
public class Sudoku {
    public static final int SIZE = 9;
    public static final int ROWS = 3;
    public static final int COLS = 3;

    private final Cell[][] cells = new Cell[SIZE][SIZE];

    private int mZerosCount;

    private Sudoku() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c] = new Cell(r, c, SIZE, 0);
            }
        }
        mZerosCount = SIZE * SIZE;
    }

    public Sudoku(String s) {
        int i = 0;
        mZerosCount = SIZE * SIZE;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c] = new Cell(r, c, SIZE, s.charAt(i) - '0');
                i++;
                if (cells[r][c].getValue() != 0) mZerosCount--;
            }
        }
        validateNotes();
    }

    public boolean setValue(int r, int c, int v) {
        if (cells[r][c].getNotes().contains(v)) {
            int value = cells[r][c].getValue();
            cells[r][c].setValue(v);
            if (value == 0) {
                mZerosCount--;
                removeNotes(r, c, v);
            }
            return true;
        }
        return false;
    }

    private void removeNotes(int r, int c, int v) {
        int sr = r / ROWS;
        int sc = c / COLS;
        for (int i = 0; i < SIZE; i++) {
            cells[r][i].removeNote(v);
            cells[i][c].removeNote(v);
            cells[sr * ROWS + i / ROWS][sc * COLS + i % COLS].removeNote(v);
        }
    }

    public int getZerosCount() {
        return mZerosCount;
    }

    public boolean isCompleted() {
        return getZerosCount() <= 0;
    }

    private void validateNotes() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                int v = cells[r][c].getValue();
                if (v == 0) continue;
                removeNotes(r, c, v);
            }
        }
    }

    public void print() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                System.out.print(cells[r][c].getValue());
                if (2 == c || 5 == c) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (2 == r || 5 == r) {
                System.out.println("-----------");
            }

        }
    }

    public Cell findSingleNoteEmptyCell() {
        for (int r = 0; r < SIZE; r++) {
            for (Cell cell : cells[r]) {
                if (cell.isEmpty() && cell.getNotes().size() == 1) {
                    return cell;
                }
            }
        }
        return null;
    }

    public static Set<Integer> valueSet() {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= SIZE; i++) {
            set.add(i);
        }
        return set;
    }

    public Cell findHiddenSingleEmptyCell() {
        Stats<Integer> stats = new Stats<>(valueSet());
        // check row keys
        for (int r = 0; r < SIZE; r++) {
            stats.reset();
            stats.increase(getRowNotes(r));
            Integer key = stats.findKey(1);
            if (key != null) {
                Cell cell = findRowCell(r, key);
                return new Cell(cell.row(), cell.col(), key);
            }
        }
        // check col keys
        for (int c = 0; c < SIZE; c++) {
            stats.reset();
            stats.increase(getColNotes(c));
            Integer key = stats.findKey(1);
            if (key != null) {
                Cell cell = findColCell(c, key);
                return new Cell(cell.row(), cell.col(), key);
            }
        }
        // find section keys
        for (int sr = 0; sr < ROWS; sr++) {
            for (int sc = 0; sc < COLS; sc++) {
                stats.reset();
                stats.increase(getSectionNotes(sr, sc));
                Integer key = stats.findKey(1);
                if (key != null) {
                    Cell cell = findSectionCell(sr, sc, key);
                    return new Cell(cell.row(), cell.col(), key);
                }
            }
        }
        return null;
    }

    public Cell findRowCell(int r, int note) {
        for (int i = 0; i < SIZE; i++) {
            if (cells[r][i].isEmpty() && cells[r][i].getNotes().contains(note)) {
                return cells[r][i];
            }
        }
        return null;
    }

    public Cell findColCell(int c, int note) {
        for (int i = 0; i < SIZE; i++) {
            if (cells[i][c].isEmpty() && cells[i][c].getNotes().contains(note)) {
                return cells[i][c];
            }
        }
        return null;
    }

    public Cell findSectionCell(int sr, int sc, int note) {
        for (int i = 0; i < SIZE; i++) {
            int ri = sr + i % ROWS;
            int ci = sc + i / COLS;
            if (cells[ri][ci].isEmpty() && cells[ri][ci].getNotes().contains(note)) {
                return cells[ri][ci];
            }
        }
        return null;
    }

    public ArrayList<Integer> getRowNotes(int r) {
        ArrayList<Integer> notes = new ArrayList<>();
        for (int c = 0; c < SIZE; c++) {
            if (cells[r][c].isEmpty()) {
                notes.addAll(cells[r][c].getNotes());
            }
        }
        return notes;
    }

    public ArrayList<Integer> getColNotes(int c) {
        ArrayList<Integer> notes = new ArrayList<>();
        for (int r = 0; r < SIZE; r++) {
            if (cells[r][c].isEmpty()) {
                notes.addAll(cells[r][c].getNotes());
            }
        }
        return notes;
    }


    public ArrayList<Integer> getSectionNotes(int sr, int sc) {
        ArrayList<Integer> notes = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            int ri = sr + i % ROWS;
            int ci = sc + i / COLS;
            if (cells[ri][ci].isEmpty()) {
                notes.addAll(cells[ri][ci].getNotes());
            }
        }
        return notes;
    }


    public void printNotes() {
        int v = 1; // 当前数字
        int r = 0;// 当前输出行
        int c = 0;// 当前输出列
        int rv = 1;// 行基数，本行行首的站位数
//        int nr;// 当前note行
//        int nc;// 当前note列
        int pr = 0;//当前屏幕打印行
        int pc;//当前屏幕打印列
        int printRowCount = SIZE * ROWS + 8;
        int printColCount = SIZE * COLS + 8;
        while (pr < printRowCount) {
            pc = 0;
            c = 0;
            // 打印第pr行
            if ((pr + 1) % (ROWS + 1) != 0) {
                // 打印数字行
                v = rv;
                while (pc < printColCount) {
                    if ((pc + 1) % (COLS + 1) != 0) {
                        // 打印数字
                        System.out.print(cells[r][c].getNotes().contains(v) ? v : 0);
//                        System.out.print(cells[r][c].getValue());
                        v++;
                    } else if ((pc + 1) % (SIZE + COLS) == 0) {
                        // 打印分区分割线
                        System.out.print('|');
                        c++;
                        v = rv;
                    } else {
                        // 打印数字分隔符
                        System.out.print(' ');
                        c++;
                        v = rv;
                    }
                    pc++;
                }
                rv += ROWS;
                if (rv > SIZE) rv = 1;
            } else {
                // 打印分隔行
                while (pc < printColCount) {
                    if ((pc + 1) % (SIZE + COLS) == 0) {
                        // 打印分区分割线
                        System.out.print('|');
                    } else {
                        // 打印数字分隔符
                        System.out.print((pr + 1) % (SIZE + ROWS) == 0 ? '=' : '-');
                    }
                    pc++;
                }
                r++;
            }
            System.out.println();
            pr++;
        }
    }

    public boolean solve() {
        while (!isCompleted()) {
            Cell cell = findSingleNoteEmptyCell();
            if (null == cell) break;
            if (!setValue(cell.row(), cell.col(), cell.getNotes().iterator().next())) {
                break;
            }
        }
        return isCompleted();
    }
}
