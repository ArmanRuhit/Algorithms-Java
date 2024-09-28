public class NQueens {
    public static void main(String[] args) {
        
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
        int maxRight = Ma

        return true;
    }
}
