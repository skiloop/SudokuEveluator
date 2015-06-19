package com.shiloop.sudoku;

import com.shiloop.sudoku.fetcher.Fetcher;
import com.shiloop.sudoku.model.SudokuPuzzle;

import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/6/20 1:49
 * Project: SudokuEveluator
 * Description:
 */
public class StepSolver {
    public void solveAll() {
        Fetcher fetcher = Fetcher.newInstance("http://s.shiloop.com/s/");
        Set<SudokuPuzzle> puzzles = fetcher.loadPuzzles(0);
        System.out.println("puzzle count:" + puzzles.size());
        for (SudokuPuzzle puzzle : puzzles) {
            System.out.println("" + puzzle.getId() + "\t" + puzzle.getContent());
        }
        int count = 0;
        for (SudokuPuzzle puzzle : puzzles) {
            if (!puzzle.isOpen()) {
                Sudoku sudoku = new Sudoku(puzzle.getContent());
                boolean solved = sudoku.solve();
                if (solved) {
                    System.out.println("" + puzzle.getId() + ",");
                    count++;
                }
            }
        }
        System.out.println("solved count:" + count);
    }

    public void hiddenSingleAll() {
        Fetcher fetcher = Fetcher.newInstance("http://s.shiloop.com/s/");
        Set<SudokuPuzzle> puzzles = fetcher.loadPuzzles(0);
        System.out.println("puzzle count:" + puzzles.size());
        Solver solver = new HiddenSingleSolver();
        for (SudokuPuzzle puzzle : puzzles) {
            System.out.println("" + puzzle.getId() + "\t" + puzzle.getContent());
        }
        int count = 0;
        for (SudokuPuzzle puzzle : puzzles) {
            if (!puzzle.isOpen() && puzzle.getLevel() > 1) {
                Sudoku sudoku = new Sudoku(puzzle.getContent());
                boolean solved = solver.solve(sudoku);
                if (solved) {
                    System.out.println("" + puzzle.getId() + ",");
                    count++;
                }
            }
        }
        System.out.println("solved count:" + count);
    }

    public void solveTest() {
        Sudoku sudoku = new Sudoku("700400900954006210102007000000100605047300129003002004380500470076000590095003061");
        SingleSolver solver = new SingleSolver();
        boolean solved = solver.solve(sudoku);
        System.out.println(solved);
        sudoku.print();
        System.out.println();
        sudoku.printNotes();
    }

    public void hiddenSingle() {
        Sudoku sudoku = new Sudoku("700400900954006210102007000000100605047300129003002004380500470076000590095003061");
        Solver solver = new HiddenSingleSolver();
        boolean solved = solver.solve(sudoku);
        System.out.println(solved);
        sudoku.print();
        System.out.println();
        sudoku.printNotes();
    }

    public static void main(String[] args) {
        StepSolver solver = new StepSolver();
        solver.hiddenSingleAll();
    }
}
