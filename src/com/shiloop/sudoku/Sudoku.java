package com.shiloop.sudoku;

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

    private Cell[][] cells = new Cell[SIZE][SIZE];

    private int mZerosCount;

    private Sudoku() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c] = new Cell(SIZE);
            }
        }
        mZerosCount = SIZE * SIZE;
    }

    public Sudoku(String s) {
        int i = 0;
        mZerosCount = SIZE * SIZE;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c] = new Cell(SIZE);
                cells[r][c].setValue(s.charAt(i) - '0');
                i++;
                if (cells[r][c].getValue() != 0) mZerosCount--;
            }
        }
        validateNotes();
    }

    public boolean setValue(int v, int r, int c) {
        if (cells[r][c].getNotes().contains(v)) {
            int value = cells[r][c].getValue();
            cells[r][c].setValue(v);
            if (value == 0) {
                mZerosCount--;
            }
            for (int i = 0; i < SIZE; i++) {
                cells[r][c].removeNote(cells[r][i].getValue());
                cells[r][c].removeNote(cells[i][c].getValue());
            }
            return true;
        }
        return false;
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
                int sr = r / ROWS;
                int sc = c / COLS;
                for (int i = 0; i < SIZE; i++) {
                    cells[r][i].removeNote(v);
                    cells[i][c].removeNote(v);
                    cells[sr * ROWS + i / ROWS][sc * COLS + i % COLS].removeNote(v);
                }
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

    public void printNotes() {
        int v = 1; // ��ǰ����
        int r = 0;// ��ǰ�����
        int c = 0;// ��ǰ�����
        int rv = 1;// �л������������׵�վλ��
//        int nr;// ��ǰnote��
//        int nc;// ��ǰnote��
        int pr = 0;//��ǰ��Ļ��ӡ��
        int pc;//��ǰ��Ļ��ӡ��
        int printRowCount = SIZE * ROWS + 8;
        int printColCount = SIZE * COLS + 8;
        while (pr < printRowCount) {
            pc = 0;
            c = 0;
            // ��ӡ��pr��
            if ((pr + 1) % (ROWS + 1) != 0) {
                // ��ӡ������
                v = rv;
                while (pc < printColCount) {
                    if ((pc + 1) % (COLS + 1) != 0) {
                        // ��ӡ����
                        System.out.print(cells[r][c].getNotes().contains(v) ? v : 0);
//                        System.out.print(cells[r][c].getValue());
                        v++;
                    } else if ((pc + 1) % (SIZE + COLS) == 0) {
                        // ��ӡ�����ָ���
                        System.out.print('|');
                        c++;
                        v = rv;
                    } else {
                        // ��ӡ���ַָ���
                        System.out.print(' ');
                        c++;
                        v = rv;
                    }
                    pc++;
                }
                rv += ROWS;
                if (rv > SIZE) rv = 1;
            } else {
                // ��ӡ�ָ���
                while (pc < printColCount) {
                    if ((pc + 1) % (SIZE + COLS) == 0) {
                        // ��ӡ�����ָ���
                        System.out.print('|');
                    } else {
                        // ��ӡ���ַָ���
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

    public void printNotes2() {
        int r = 0;
        int rn = 0;// ���ظ�����
        int rv = 1;//�п�ͷ������
        while (rn < ROWS) {
            int c = 0;
            int cn = 0;//���ظ�����
            int cv = rv;// �д�ͷ����
            while (cn < COLS) {
                if (cells[r][c].getNotes().contains(cv)) {
                    System.out.print(cv);
                } else {
                    System.out.print(0);
                }
                // ����зָ��ַ�
                if (c == SIZE - 1 && cn != COLS - 1) {
                    System.out.print("|");
                } else if ((c + 1) % COLS == 0) {
                    System.out.print(" ");
                }
                c = (c + 1) % SIZE;
                cn += c == 0 ? 1 : 0;
                cv = (cv + 1 - rv) % COLS + rv;
            }
            // ����
            System.out.println();
            // ����ָ���
            if (r == SIZE - 1 && rn != ROWS - 1) {
                System.out.println("===================================");
            } else if ((r + 1) % ROWS == 0 && (r + 1) != SIZE) {
                System.out.println("-----------|-----------|-----------");
            }
            rv += ROWS;
            rv %= SIZE;
            r = (r + 1) % SIZE;
            rn += (r == 0 ? 1 : 0);
        }
    }
}
