/*
Thomas Griffith
date October 10, 2018
Class 2150 Homework2
file: IGameBoard
 */
package cpsc2150.connectX;




/**
 * a 2D structure  containing char values
 * where the element is added to a certain
 * column and put at the lowest possible row
 * out of two people whoever can win by getting some
 * in a row wins and can change the size (connect four board)
 *
 * This structure is bounded by MAX_Row
 * This structure is bounded by MAX_Column
 *
 * Initialization ensures: gameBoard contains only blank
 * characters and is MAX_SIZE x MAX_SIZE or smaller
 *
 * Defines: number_of_rows: Z
 *          number_of_columns: Z
 *          number_To_Win: Z
 * Constraints: 2 < number_of_rows <= MAX_SIZE
 * 		        2 < number-of_cols <= MAX_SIZE
 * 		        2 < number-of_cols <= MAX_SIZE
 */
public interface IGameBoard {
    int Max_Row = 100;
    int Max_Column = 100;






    /**
     *
     * @param c is the column number that will be checked and see if it is free or full
     * @return true if the 'c' param column number that has an unempty row
     * @return false if the 'c' param column number that has no unempty rows
     * @pre 0 <= c < number_of_cols
     * @post returns true or false so that the Connect4Game can see if the user inputted a correct column
     */
   default boolean checkIfFree(int c)
   {
       int r = getNumRows() - 1;
       //checks to see if the column user wants to put one in is full or not
       if(whatsAtPos(r, c) == ' ')
       {
           return true;
       }
       return false;
   }





    /**
     *
     * @param c the column so to see if the player X or O would win on last move made
     * @return true if the last move with'c' will cause a win horiz, vert, and diag
     * @pre 0 <= c < number_of_cols
     * @post true or false and tells Connect4Game if a player had won
     */
   default boolean checkForWin(int c)
    {



        int row = 0;//row of the last move, possible for win
        char token = ' ';//the token placed on the previous move
        int finder = getNumRows() - 1;//counter to find the last row with token in it
        boolean foundToken = false;//that a token was found
        boolean didWin = false;//if found a token and it relates to a win return boolean



        //loop to cycle column for last token top to bottom, when found one end loop
        while(finder >= 0)
        {
            //if the row has either X or O token, make row and token equal the found row and token and end loop
            if(whatsAtPos(finder, c) != ' ')
            {
                row = finder;
                token = whatsAtPos(finder, c);
                finder = -1;
                foundToken = true;
            }
            finder--;
        }


        if(foundToken == true)
        {
            //check to see if the new found set was a winning move diagonally
            if(checkDiagWin(row, c, token) == true)
            {
                didWin = true;

            }
            //check to see if the new found set was a winning move horizontally
            if(checkHorizWin(row,c,token) == true)
            {
                didWin = true;
            }
            //check to see if the new found set was a winning move vertically
            if(checkVertWin(row,c,token) == true)
            {
                didWin = true;
            }

        }



//if one of the previous check for win functions come up true, it will be a victor
        return didWin;
    }





    /**
     *
     * @param p either a 'X' or 'O' token to be placed in the lowest available row if not full
     * @param c the column to put the param p in its row
     * @pre p == X, p == O, gameBoard != Full
     * @post put the token p in the Board game array in column c
     */
    void placeToken(char p, int c);











    /**
     *
     * @param r row the token was placed in
     * @param c column the token was placed in
     * @param p player token
     * @return true if there are numberToWin tokens each on the same row next to each other
     * @return false if there are no numberToWin of the same token next to each other on same row different col
     * @pre 0 <= r < number_of_rows
     * @pre 0 <= c < number_of_cols
     * @pre  p == 'X' or p == 'O'
     * @post p is in row r and column c whether win or continuation
     */
    default boolean checkHorizWin(int r, int c, char p)
    {
        //same row different columns left and right


        int totalTokens = 0;//count how many of same token is next to each other
        boolean winner = false;//if four or more in a row it is true
        int columnCheck = c; // int to hold the column at position of check
        //loops to check each possible way to get four in a row
        boolean loopSpots = false;// loop to go through possible places



        //cycle through the columns equal or greater that are possible than the initial check spot
        while(loopSpots == false)
        {

            //check to see if the spot has the same token as the one we are looking for
            //if so add it to the list or end loop or if out of bounds end loop
            if(columnCheck < getNumColumns())
            {
                if (whatsAtPos(r, columnCheck) == p) {
                    //increase the number in a row or end loop
                    totalTokens++;
                } else {
                    loopSpots = true;//end loop if not same token
                }
            }else {
                loopSpots = true;//end loop if out of bounds
            }

            columnCheck++; // increase column to look at

        }





        loopSpots = false;//reset boolean for other side of point
        columnCheck = c-1;//set to check one less
        //cycle through the columns less than the initial check spot
        while(loopSpots == false)
        {

            //check to see if the spot has the same token as the one we are looking for
            //if so add it to the list or end loop or out of bounds end loop
            if(columnCheck >= 0)
            {
                if (whatsAtPos(r, columnCheck) == p) {
                    //increase the number in a row or end loop
                    totalTokens++;
                } else {
                    loopSpots = true;//end loop if token is not the same
                }
            }else {
                loopSpots = true;// end loop if out of bounds
            }

            columnCheck--;//decrease column number to look at

        }



        //if number in a row is equal or greater to number needed to win winner true
        if(totalTokens >= getNumToWin())
        {
            winner = true;
        }


        return winner;
    }








    /**
     *
     * @param r row the token was placed in
     * @param c column the token was placed in
     * @param p player token
     * @return true if there are numToWin tokens each on the same column next to each other
     * @return false if there are no numToWin of the same token next to each other
     * @pre 0 <= r < number_of_rows
     * @pre 0 <= c < number_of_cols
     * @pre  p == 'X' or p == 'O'
     * @post p is in row r and column c whether win or continuation
     */
    default boolean checkVertWin(int r, int c, char p)
    {

        int totalTokens = 0;//count how many of same token is next to each other
        boolean winner = false;//if four or more in a row it is true
        int rowCheck = r;
        //loops to check each possible way to get four in a row
        boolean loopSpots = false;



        //cycle through the columns equal or greater than the initial check spot
        while(loopSpots == false)
        {

            //check to see if the spot has the same token as the one we are looking for
            //if so add it to the list or end loop or out of bounds end loop
            if(rowCheck < getNumRows())
            {
                if (whatsAtPos(rowCheck, c) == p) {
                    //increase the number in a row or end loop
                    totalTokens++;
                } else {
                    loopSpots = true;//end loop if token is not the same
                }
            }else {
                loopSpots = true;// end loop if out of bounds
            }

            rowCheck++;

        }





        loopSpots = false;//reset values
        rowCheck = r-1;//one less as to not repeat
        //cycle through the columns less than the initial check spot
        while(loopSpots == false)
        {

            //check to see if the spot has the same token as the one we are looking for
            //if so add it to the list or end loop or out of bounds end loop
            if(rowCheck >= 0)
            {
                if (whatsAtPos(rowCheck, c) == p) {
                    //increase the number in a row or end loop
                    totalTokens++;
                } else {
                    loopSpots = true;//end loop if token is not the same
                }
            }else {
                loopSpots = true;// end loop if out of bounds
            }

            rowCheck--;

        }

        //if four in a row make winner true
        if(totalTokens >= getNumToWin())
        {
            winner = true;
        }


        return winner;




    }









    /**
     *
     * @param r row the token was placed in
     * @param c column the token was placed in
     * @param p player token
     * @return true if there are numToWin same tokens
     * as row and column either (r+1,c+1), (r-1, c+1), (r+1, c-1), (r-1,c-1)
     * @return false if true is not proven
     * @pre 0 <= r < number_of_rows
     * @pre 0 <= c < number_of_cols
     * @pre  p == 'X' or p == 'O'
     * @post p is in row r and column c whether win or continuation true/false
     */
    default boolean checkDiagWin(int r, int c, char p)
    {
        boolean didWin = false;
        int numInRow = 0;//number of tokens as (r+1 c+1) and (r-1 c-1) in a row
        int numInRow2 = 0;//number of tokens as (r+1 c-1) and (r-1 c+1) in a row
        int rowCheck = r;
        int columnCheck = c;
        //loops to check each possible way to get four in a row
        boolean loopSpots = false;
        boolean loopSpots2 = false;
        boolean loopSpots3 = false;
        boolean loopSpots4 = false;


        //loop to check the diag as both r and c increase
        while(loopSpots == false)
        {

            //check to see if the spot has the same token as the one we are looking for
            //if so add it to the list

            if(rowCheck < getNumRows() && columnCheck < getNumColumns())
            {
                if (whatsAtPos(rowCheck, columnCheck) == p) {
                    //increase the number in a row or end loop
                    numInRow++;
                } else {
                    loopSpots = true;//if new token is wrong end loop
                }
            }else {
                loopSpots = true;//end loop when out of bounds
            }

            rowCheck++;
            columnCheck++;

        }





        rowCheck = r-1;
        columnCheck = c-1;

        //loop to check the diag as both r and c decrease
        while(loopSpots2 == false)
        {



            //check to see if the spot has the same token as the one we are looking for
            //if so add it to the list
            if(rowCheck >= 0 && columnCheck >= 0)
            {
                if (whatsAtPos(rowCheck, columnCheck) == p) {
                    //increase the number in a row or end loop
                    numInRow++;

                } else {
                    loopSpots2 = true;//if new token is wrong end loop
                }
            }else {
                loopSpots2 = true;//end loop when out of bounds
            }

            rowCheck--;
            columnCheck--;

        }







        rowCheck = r;
        columnCheck = c;
        //loop to check the diag as r decrease and c increase
        while(loopSpots3 == false)
        {

            //if in bound of array check to see if it matches the
            // token if it does add it and continue loop, if not end loop
            if(rowCheck >= 0 && columnCheck < getNumColumns())
            {

                if (whatsAtPos(rowCheck, columnCheck) == p) {
                    numInRow2++;
                } else {
                    loopSpots3 = true;//if new token is wrong end loop
                }
            }else{
                loopSpots3 = true;//end loop when out of bounds
            }
            rowCheck--;
            columnCheck++;



        }


        rowCheck = r+1;
        columnCheck = c-1;
        //loop to check the diag as c decrease and r increase
        while(loopSpots4 == false)
        {

            //if in bound of array check to see if it matches the
            // token if it does add it and continue loop, if not end loop
            if(rowCheck < getNumRows() && columnCheck >= 0)
            {
                if (whatsAtPos(rowCheck, columnCheck) == p) {
                    numInRow2++;

                } else {
                    loopSpots4 = true;//if new token is wrong end loop
                }
            }else{
                loopSpots4 = true;//end loop when out of bounds
            }
            rowCheck++;
            columnCheck--;

        }






        //checks to see if there were a total in diag path equal or greater than number needed to win
        if(numInRow >= getNumToWin())
        {
            didWin = true;
        }

        if(numInRow2 >= getNumToWin())
        {
            didWin = true;
        }


        return didWin;

    }







    /**
     *
     * @param r row to go to
     * @param c column to go to
     * @return char charAt where the cordinates (r,c) point to in array
     * @pre 0 <= r < number_of_rows
     * @pre 0 <= c < number_of_cols
     * @post a char value is examined to see whose token it belongs to or if empty
     */
      char whatsAtPos(int r, int c);






    /**
     *
     * @return a string to be printed in the main class
     * @pre gameBoard != NULL
     * @post the board is examined and put in a string to be sent to the main class
     */
    String toString();










    /**
     *
     * @return true if the game board results in a tie game, otherwise it returns false.
     * @pre gameBoard != NULL
     * @post see if there is a tie on a full board
     */
    default boolean checkTie()
    {
        int allFull = 0;
        // for loop to see if every spot is full if so return true else tie is false

        for(int c = 0; c < getNumColumns(); c++)
        {
            if(!checkIfFree(c))
            {
                allFull++;
            }

        }

        if(allFull == getNumColumns())
        {
            return true;
        }

        return false;

    }




    /**
     *
     * @return the int number of rows in gameBoard
     * @pre gameBoard != NULL;
     * @post a int that that counted the number of rows
     */
    int getNumRows();





    /**
     *
     * @return the int number of rows in gameBoard
     * @pre gameBoard != NULL;
     * @post a int that that counted the number of columns
     */
    int getNumColumns();





    /**
     *
     * @return the int number of rows in gameBoard
     * @pre gameBoard != NULL;
     * @post a int that that is the amount in a row to win the game
     */
    int getNumToWin();







}
