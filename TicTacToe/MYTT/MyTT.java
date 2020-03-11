import java.util.Scanner;
import java.io.*;

public class MyTT {

    private char[][] board;/*This variable is a 2D array of characters that will be representative of the 
    three-by-three board of a game of Tic-Tac-Toe. It will hold the state of 
    the game inside of the TTT object at any given time.*/
    private char currentPlayerMark;/*This variable will hold either an ‘x’ or an ‘o’, representing which player’s 
    turn it is at any given point of time. The methods of the TTT class will use this when marking the board to determine 
    which type of mark will be placed.*/

    /*This is the constructor. It will be responsible for ensuring the board gets initialized properly,
     and for setting who the first player will be.*/
    public MyTT() {
        board = new char[3][3];
        currentPlayerMark = 'x';
        initializeBoard();
    }

    //Gives us access to currentPlayerMark
    public char getCurrentPlayerMark()
    {
        return currentPlayerMark;
    }


    // Set/Reset the board back to all empty values.
    //This method will initialize the board variable such that all slots are empty.
    public void initializeBoard() {

        // Loop through rows
        for (int i = 0; i < 3; i++) {

            // Loop through columns
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    //This method will print the Tic-Tac-Toe board to standard output.
    // Print the current board (may be replaced by GUI implementation later)
    public void printBoard() {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    //This method will check whether or not the board is full. It will return true if the board is full and a false otherwise.
    // Loop through all cells of the board and if one is found to be empty (contains char '-') then return false.
    // Otherwise the board is full.
    public boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }

        return isFull;
    }

     //This method will check to see if a player has won, and if so, it will return true.
    // Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

   //This method will specifically check the rows for a win.
    // Loop through rows and see if any are winners.
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    //This method will specifically check the columns for a win.
    // Loop through columns and see if any are winners.
    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    //This method will specifically check the diagonals for a win.
    // Check the two diagonals to see if either is a win. Return true if either wins.
    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || 
        (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }

    //This method will check the three specified characters taken in to see if all three 
    //are the same ‘x’ or ‘o’ letter. If so, it will return true.
    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    
    // Change player marks back and forth.
    public void changePlayer() {
        if (currentPlayerMark == 'x') {
            currentPlayerMark = 'o';
        }
        else {
            currentPlayerMark = 'x';
        }
    }

    // Places a mark at the cell specified by row and col with the mark of the current player.
    public boolean placeMark(int row, int col) {

        // Make sure that row and column are in bounds of the board.
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayerMark;
                    return true;
                }
            }
        }

        return false;
    }


    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in); 
        MyTT game = new MyTT();
        game.initializeBoard();
        System.out.println("Tic-Tac-Toe!");
        do
        {
            System.out.println("Current board layout:");
            game.printBoard();
            int row;
            int col;
            do
            {
                System.out.println("Player " + game.getCurrentPlayerMark() + ", enter an empty row and column to place your mark!");
                row = scan.nextInt()-1;
                col = scan.nextInt()-1;
            }
            while (!game.placeMark(row, col));
            game.changePlayer();
        }
        while(!game.checkForWin() && !game.isBoardFull());
        if (game.isBoardFull() && !game.checkForWin())
        {
            System.out.println("The game was a tie!");
        }
        else
        {
            System.out.println("Current board layout:"); 
            game.printBoard();
            game.changePlayer();
            System.out.println(Character.toUpperCase(game.getCurrentPlayerMark()) + " Wins!");
        }
    }



}