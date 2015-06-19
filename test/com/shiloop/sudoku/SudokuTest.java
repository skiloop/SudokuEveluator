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
        String data = "000000102005200804010090060750062000006300000130049000020050070007400503000000209";
        Sudoku s = new Sudoku(data);
        s.print();
        System.out.println("Notes:");
        System.out.println();
        s.printNotes();
    }
}
