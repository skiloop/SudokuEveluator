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
    public static void main(String[] args) {
        Fetcher fetcher = Fetcher.newInstance("http://s.shiloop.com/s/");
        Set<SudokuPuzzle> puzzles = fetcher.loadPuzzles(0);
        System.out.println("puzzle count:" + puzzles.size());
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
}
