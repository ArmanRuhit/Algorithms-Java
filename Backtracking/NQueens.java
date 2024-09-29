/**
 * This is an implementation of the N-Queens problem using backtracking.
 * The goal of the problem is to place 'n' queens on an n x n chessboard such that 
 * no two queens can attack each other. This means no two queens can share the same row, 
 * column, or diagonal.
 *
 * Algorithm:
 * - The `queens` function attempts to place queens row by row.
 * - For each row, it tries to place a queen in each column and checks if it is safe to do so 
 *   using the `isSafe` function.
 * - If a valid placement is found (i.e., it is safe), the queen is placed and the algorithm 
 *   proceeds to the next row (recursive call).
 * - If placing a queen leads to a solution, the board is displayed using the `display` method.
 * - If no valid placement exists for a particular configuration, the algorithm backtracks 
 *   by removing the last placed queen and trying the next possible column.
 * - The `isSafe` function checks if a queen can be safely placed by verifying the current column, 
 *   and both diagonals (upper-left and upper-right) to ensure no other queens can attack.
 * - The `queens` function returns the number of valid solutions found, and prints all possible 
 *   configurations of the board.
 *
 * Time Complexity: O(n!) - The algorithm explores all possible queen placements.
 * Space Complexity: O(n^2) - Space required for the board and the recursion stack.
 */

public class NQueens {
    public static void main(String[] args) {
        int n = 4;
        boolean[][] board = new boolean[n][n];
        System.out.println(queens(board, 0));
    }

    static int queens(boolean[][] board, int row){
        if(row == board.length){
            display(board);
            System.out.println();
            return 1;
        }

        int count = 0;

        // placing the queenn and checking for every row and col
        for(int col = 0; col < board.length; col++){
            if(isSafe(board, row, col)){
                board[row][col] = true;
                count+= queens(board, row+1);
                board[row][col] = false;
            }
        }

        return count;
    }

    static void display(boolean[][] board){
        for(boolean[] row: board){
            for(boolean element : row){
                if(element){
                    System.out.print("Q ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    static boolean isSafe(boolean[][] board, int row, int col){
        // check vertical row
        for(int i = 0; i < row; i++){
            if(board[i][col]){
                return false;
            }
        }

        // diagonal left
        int maxLeft = Math.min(row, col);
        for(int i = 1; i <= maxLeft; i++){
            if(board[row-i][col-i]){
                return false;
            }
        }


        // diagonal right
        int maxRight = Math.min(row, board.length - col - 1);

        for(int i = 1; i <= maxRight; i++){
            if(board[row-i][col+i]){
                return false;
            }
        }

        return true;
    }
}
