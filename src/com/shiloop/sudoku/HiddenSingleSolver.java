package com.shiloop.sudoku;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/6/20 5:38
 * Project: SudokuFetcher
 * Description:
 */
public class HiddenSingleSolver extends Solver {


    @Override
    public boolean solve(Sudoku sudoku) {
        if (null == sudoku) return true;
        while (!sudoku.isCompleted()) {
            Cell cell = sudoku.findHiddenSingleEmptyCell();
            if (null == cell) break;
//            System.out.println("" + cell.row() + "\t" + cell.col() + "\t" + cell.getValue());
            if (!sudoku.setValue(cell.row(), cell.col(), cell.getValue())) {
                break;
            }
        }
        return sudoku.isCompleted();
    }
}
