/*
Thomas Griffith
date October 10, 2018
Class 2150 Homework2
file: GameBoard
 */
package cpsc2150.connectX;
/**
 *
 *@invariant 0 < rowEntered <= MAX_SIZE
 *@invariant 0 < columnEntered <= MAX_SIZE
 * Correspondence number_of_rows = rowEntered
 * Correspondence number_of_columns = columnEntered
 *Correspondence this = gameBoard[0...numRow-1][0...numCol-1]
 */
public class GameBoard implements IGameBoard{



    private char[][] gameBoard;//the boardgame
    private int numberToWin;//amount needed to win
    private int rowEntered;//max row for gameboard
    private int columnEntered;//max column for gameboard to have





    /**
     *
     * @param c user input for the number of columns to play with
     * @param r user input for the number of rows to play with
     * @param numToWin user input for the number of tokens in a row to win
     * @pre 3 <= c < 100
     * @pre 3 <= r < 100
     * @pre 3 <= numToWin < 25
     * @post gameBoard[r][c] array is filled with spaced char and an empty board is created
     * @post sets the private variables of the instance of gameBoard with the input
     */
    public GameBoard(int c, int r, int numToWin)
    {
        System.out.println("Fast Board.");
        //makes a gameboard full of spaced chars
        gameBoard = new char [r][c];
        numberToWin = numToWin;
        rowEntered = r;
        columnEntered =c;

        for(int rows = 0; rows < r; rows++)
        {
            for(int columns = 0; columns < c; columns++)
            {
                //makes ameBoard filled with space for tokens
                gameBoard[rows][columns] = ' ';
            }

        }
    }







    //primary
    public void placeToken(char p, int c)
    {
        int row;//use to find a row with no token in it

        //go through rows bottom to top to find the next blank spot for token
        for(row = 0; row < rowEntered; row++)
        {
            //checks to see what is at position and if it is not a token, put in the new token
            if(whatsAtPos(row,c) == ' ')
            {
                gameBoard[row][c] = p;
                row = rowEntered;
            }
        }

    }




   //primary
   public char whatsAtPos(int r, int c)
    {

        return gameBoard[r][c];
        //returns the value located at the r,c point either X or O if neither return space
        /*
        if(gameBoard[r][c] == 'X')
        {
           return 'X';
        } else if(gameBoard[r][c] == 'O')
        {
            return 'O';
        }
        else{
            return ' ';
        }
        */

    }




    //primary
    public String toString()
    {

        //put the array into a string incuding the column numbers with a double for loop
        String arrayPrint = "|";
        for(int enterCs = 0; enterCs < columnEntered; enterCs++)
        {
            //if column number is less than 2 digits, add a space to even it
            if(enterCs < 10)
            {
                arrayPrint = arrayPrint + " " + enterCs + "|";
            }else{
                //since it is two digit, just add it to string to print
                arrayPrint = arrayPrint + enterCs + "|";
            }
        }

        arrayPrint = arrayPrint + "\n";

        for(int rows = rowEntered-1; rows >= 0; rows--)
        {

            for(int columns = 0; columns < columnEntered; columns++)
            {
                //adds the placeholder of location plus space to even the double digit columns
                arrayPrint = arrayPrint + "|" +gameBoard[rows][columns] + " ";
            }
            //row divisor new line
            arrayPrint = arrayPrint + "|\n";
        }
        return arrayPrint;
    }



    //primary
    public int getNumRows()
    {
        //return max rows entered
        return rowEntered;
    }





    //primary
    public int getNumColumns()
    {
        //return max columns entered
        return columnEntered;
    }





    //primary
    public int getNumToWin()
   {
       //return the amount in a row to win
       return numberToWin;
   }

}
