package com.shiloop.sudoku;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/6/19 12:23
 * Project: SudokuEveluator
 * Description:
 */
public abstract class Solver {
    Sudoku mSudoku;

    public Solver(Sudoku sudoku) {
        mSudoku = sudoku;
    }

    public Solver(String sudoku) {
        mSudoku = new Sudoku(sudoku);
    }

    public abstract void solve();
}
