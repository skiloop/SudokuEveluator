package com.shiloop.sudoku;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/6/19 12:34
 * Project: SudokuEveluator
 * Description:
 */
public class CandidatesSolver extends Solver {

    @Override
    public boolean solve(Sudoku sudoku) {
        if (null == sudoku) return true;
        while (!sudoku.isCompleted()) {
            Cell cell = sudoku.findSingleNoteEmptyCell();
            if (null == cell) break;
            cell.setValue(cell.getNotes().iterator().next());
        }
        return sudoku.isCompleted();
    }
}
