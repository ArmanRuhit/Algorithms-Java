/**
 * This is an implementation of the N-Knights problem using backtracking.
 * The objective is to place 'n' knights on an n x n chessboard such that 
 * no two knights can attack each other. Knights move in an "L" shape and 
 * can attack squares that are two squares in one direction and one square 
 * in a perpendicular direction.
 *
 * Algorithm:
 * - The `knight` function attempts to place knights on the board one by one.
 * - For each square (row, col), the algorithm checks whether placing a knight is safe 
 *   using the `isSafe` function.
 * - The `isSafe` function checks if a knight can be placed at the current position by 
 *   validating that no other knight can attack this position. It looks for potential knight 
 *   moves from previously placed knights in all directions.
 * - The `knight` function places a knight if it's safe and recursively tries to place 
 *   the remaining knights in the next column. If placing a knight leads to a valid 
 *   solution, the configuration is printed using the `display` method.
 * - If no valid configuration is possible for a given placement, the function backtracks 
 *   by removing the last placed knight and trying the next possible square.
 * - The algorithm stops and prints all valid solutions once all knights have been successfully placed.
 *
 * Time Complexity: O(n^2) - The algorithm explores every possible configuration of knight placements.
 * Space Complexity: O(n^2) - Space is used for the board and the recursive call stack.
 */


public class NKnights {
    
    public static void main(String[] args) {
        int n = 4;
        boolean[][] board = new boolean[n][n];

        knight(board, 0, 0, n-1);
    }

    static void knight(boolean[][] board, int row, int col, int knights){
        if(knights == 0){
            display(board);
            System.out.println();
            return;    
        }

        if(row == board.length - 1 && col == board.length){
            return;
        }

        if(col == board.length){
            knight(board, row+1, 0, knights);
            return;
        }

        if(isSafe(board, row, col)){
            board[row][col] = true;
            knight(board, row, col+1, knights - 1);
            board[row][col] = false;
        }

        knight(board, row, col+1, knights);
    }

    static boolean isSafe(boolean[][] board, int row, int col){

        if(isValid(board, row-2, col+1)){
            if(board[row-2][col+1]){
                return false;
            }
        }

        if(isValid(board, row-2, col-1)){
            if(board[row-2][col+1]){
                return false;
            }
        }

        if(isValid(board, row-1, col-2)){
            if(board[row-1][col-2]){
                return false;
            }
        }

        if(isValid(board, row-1, col+2)){
            if(board[row-1][col+2]){
                return false;
            }
        }

        return true;
    }

    static boolean isValid(boolean[][] board, int row, int col){
        return (row >= 0 && row < board.length && col >=0 && col < board.length);
    }

    static void display(boolean[][] board){
        for(boolean[] row : board) {
            for(boolean element : row) {
                if (element) {
                    System.out.print("K ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}
