/*
Thomas Griffith
date October 10, 2018
Class 2150 Homework2
file: GameBoard
 */
package cpsc2150.connectX;

import javax.lang.model.type.NullType;
import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *@invariant 0 < rowEntered <= MAX_SIZE
 *@invariant 0 < columnEntered <= MAX_SIZE
 * Correspondence number_of_rows = rowEntered
 * Correspondence number_of_columns = columnEntered
 *Correspondence this = gameBoard[0...numRow-1][0...numCol-1]
 * Correspondence this = gameBoard.get(numCol-1).get(numRow -1)
 */
public class GameBoardMem implements IGameBoard {








    private int numberToWin;//amount needed to win
    private int rowEntered;//max row for gameboard
    private int columnEntered;//max column for gameboard to have
    List<Character> myList;
    private Map<Integer, List<Character>> gameBoard;



    /**
     *
     * @param c user input for the number of columns to play with
     * @param r user input for the number of rows to play with
     * @param numToWin user input for the number of tokens in a row to win
     * @pre 3 <= c < 100
     * @pre 3 <= r < 100
     * @pre 3 <= numToWin < 25
     * @post instance of gameBoardCols is made as empty and blank
     * @post sets the private variables of the instance of gameBoard with the input
     */
    public GameBoardMem(int c, int r, int numToWin)
    {
        System.out.println("Memory effic.");
        numberToWin = numToWin;
        rowEntered = r;
        columnEntered =c;

        List<Character> myList3;
        gameBoard = new HashMap<>(c);

        for(int i =0; i < c; i++)
        {
            myList3 = new ArrayList<>(r);
            gameBoard.put(i,myList3);

        }

    }





    //primary
    public void placeToken(char p, int c)
    {

        //add char p to map
        gameBoard.get(c).add(p);


    }




    //primary
    public char whatsAtPos(int r, int c)
    {


       //gameBoard.get(c).get(r);
        //returns the value located at the r,c point either X or O if neither return space


                //adds the placeholder of location plus space to even the double digit columns
                if (!gameBoard.get(c).isEmpty()) {

                    if (gameBoard.get(c).size() > r) {
                        return gameBoard.get(c).get(r);
                    }
                }
                else{
                    return ' ';
                }

        return ' ';
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
                if(!gameBoard.get(columns).isEmpty()) {

                    //add the char at row if it exists else add a blank
                    if(gameBoard.get(columns).size() > rows)
                    {
                        arrayPrint = arrayPrint + "|"  + gameBoard.get(columns).get(rows) + " ";
                    }
                    else{
                        arrayPrint = arrayPrint + "|  ";
                    }


                }
                //if nothing in for row, add spaces
                else{
                    arrayPrint = arrayPrint + "|  ";
                }
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