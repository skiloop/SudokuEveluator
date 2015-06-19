package com.shiloop.sudoku;

/**
 * Created with IntelliJ IDEA
 * Author : skiloop@126.com
 * Time   : 2015/4/11 11:11
 * Project: SudokuEveluator
 * Description:
 */
public class SudokuTest {
    public static void main(String[] args) {
        String data = "700400900954006210102007000000100605047300129003002004380500470076000590095003061";
        Sudoku s = new Sudoku(data);
        s.print();
        System.out.println("Notes:");
        System.out.println();
        s.printNotes();
    }
}
