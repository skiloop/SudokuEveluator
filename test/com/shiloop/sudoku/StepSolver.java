package com.shiloop.sudoku;

import com.shiloop.sudoku.fetcher.Fetcher;
import com.shiloop.sudoku.fetcher.HttpFetcher;
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
        CandidatesSolver solver = new CandidatesSolver();
        int count = 0;
        for (SudokuPuzzle puzzle : puzzles) {
            if (!puzzle.isOpen()) {
                boolean solved = solver.solve(new Sudoku(puzzle.getContent()));
                if (solved) {
                    System.out.println("" + puzzle.getId());
                    count++;
                }
            }
        }
        System.out.println("solved count:" + count);
    }

    public void solveTest() {
        Sudoku sudoku = new Sudoku("700400900954006210102007000000100605047300129003002004380500470076000590095003061");
        CandidatesSolver solver = new CandidatesSolver();
        boolean solved = solver.solve(sudoku);
        System.out.println(solved);
    }

    public static void main(String[] args) {
        StepSolver solver = new StepSolver();
        solver.solveTest();
    }
}
