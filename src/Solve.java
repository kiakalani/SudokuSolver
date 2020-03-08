import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Kia Kalani
 * @version 1.00
 * <code>Solve</code> class is responsible for solving a given board to it.
 */
public class Solve {
    /**
     * The constructor
     */
    private Solve() {

    }

    /**
     * This method indicates if a move is valid.
     * @param row the given row
     * @param column the given column
     * @param ans is the key for finding out whether it is valid or not
     * @param board is the sudoku board
     * @return true if the answer is valid
     */
    private static boolean isValid(int row, int column, int ans, int[][] board) {
        for (int[] ints : board) {
            if (ints[column] == ans) {
                return false;
            }
        }
        for (int c = 0; c < board[0].length; c++) {
            if (board[row][c] == ans) {
                return false;
            }
        }
        int startRow = row - (row % 3);
        int startColumn = column - (column % 3);
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startColumn; c < startColumn + 3; c++) {
                if (board[r][c] == ans) return false;
            }
        }
        return true;
    }

    /**
     * This method provides all of the empty spots in the board. i.e. the spots with the content 0
     * @param board is the sudoku board
     * @return the rows and columns of the valid positions
     */
    private int[][] emptyPositions(int[][] board) {
        ArrayList<Integer> emptyRows = new ArrayList<>();
        ArrayList<Integer> emptyColumns = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0) {
                    emptyRows.add(r);
                    emptyColumns.add(c);
                }
            }
        }
        int[][] empty = new int[emptyRows.size()][2];
        for (int i = 0; i < emptyRows.size(); i++) {
            empty[i][0] = emptyRows.get(i);
            empty[i][1] = emptyColumns.get(i);
        }
        return empty;
    }

    /**
     * This method is responsible for solving the board.
     * @param board is the sudoku board.
     * @return true if the board is solved.
     */
    private boolean solve(int[][] board) {
        int[][] empty = emptyPositions(board);
        if (empty.length == 0) {
            return true;
        }
        for (int i = 1; i < 10; i++) {
            if (isValid(empty[0][0], empty[0][1], i, board)) {
                board[empty[0][0]][empty[0][1]] = i;
                if (solve(board)) {
                    return true;
                } else board[empty[0][0]][empty[0][1]] = 0;
            }
        }
        return false;
    }

    /**
     * This method creates a copy of the 2d arrays of integers
     * @param original is the original array
     * @return a copy of the array
     */
    public int[][] copy(int[][] original) {
        int[][] cp = new int[original.length][original[0].length];
        for (int i=0;i<original.length;i++) {
            for (int j=0;j<original[i].length;j++) {
                cp[i][j] = original[i][j];
            }
        }
        return cp;
    }

    /**
     * This method is responsible for returning a solved board
     * @param board is the sudoku board.
     * @return the solved sudoku board
     */
    private int[][] solvedBoard(int[][] board) {
        int[][] boardCopy = copy(board);
        if (solve(boardCopy)) {
            return boardCopy;
        }else System.out.println("Error solving");
        return board;
    }

    /**
     * This method provides the solved answer to the board
     * @param board is the sudoku board
     * @return a solved version of the board
     */
    public static int[][] getSolvedAnswer(int[][] board) {
        return new Solve().solvedBoard(board);
    }

    /**
     * This method reads a board from a file.
     * @param path refers to the path of the file.
     * @return the board from the file as a 2d array of integers.
     */
    public static int[][] readBoard(String path) {
        try {
            Scanner reader = new Scanner(new File(path));
            int[][] board = new int[9][9];
            int row = 0;
            while (reader.hasNextLine()) {
                String cur = reader.nextLine();
                for (int column =0;column<cur.length();column++) {
                    board[row][column] = Integer.parseInt(cur.charAt(column)+"");
                }
                row++;
            }
            return board;
        }catch (IOException err) {
            System.out.println("Error reading file");
        }
        return new int[0][0];
    }


}
