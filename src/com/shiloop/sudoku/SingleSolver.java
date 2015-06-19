package com.shiloop.sudoku;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/6/19 12:34
 * Project: SudokuEveluator
 * Description:
 */
public class SingleSolver extends Solver {

    @Override
    public boolean solve(Sudoku sudoku) {
        if (null == sudoku) return true;
        while (!sudoku.isCompleted()) {
            Cell cell = sudoku.findSingleNoteEmptyCell();
            if (null == cell) break;
            System.out.println("" + cell.row() + "\t" + cell.col() + "\t" + cell.getValue() + "\t" + cell.getNotes().iterator().next());
            if (!sudoku.setValue(cell.row(), cell.col(), cell.getNotes().iterator().next())) {
                break;
            }
        }
        return sudoku.isCompleted();
    }
}
