/*
Thomas Griffith
date October 10, 2018
Class 2150 Homework2
file: Connect4Game
 */

package cpsc2150.connectX;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Connect4Game {

    //function to print the BoardGame
    static void printBoardGame(String boardPrint)
    {

        System.out.println(boardPrint);

    }





    public static void main(String[] args) {




        boolean Again = true;

        while (Again) {//loop to play a new game if player says yes
            int rows = 6;//default number so no error, will change by user
            int columns = 7;//default number so no error, will change by user
            int needToWin = 4;//default number so no error, will change by user
            Scanner reader = new Scanner(System.in);
            boolean Acceptable = false;
            char[] playerChar = new char[10];
            playerChar[0] = 'X';
            playerChar[1] = 'O';
            int numOfPlayers = 0;
            int WhoseUp = 0;


            //loop to get correct number of players input
            while(!Acceptable)
            {

                System.out.println("How many players?");
                numOfPlayers = reader.nextInt();
                //check to see if row is les than three
                if(numOfPlayers < 2)
                {
                    System.out.println("Must be at least 2 players");
                }
                //checks to see if row is greater than 100
                else if(numOfPlayers > 10)
                {
                    System.out.println("Must be 10 players or fewer");
                }else{
                    //end loop if 2 <= numOfPlayers <= 10
                    Acceptable = true;
                }

            }
            Acceptable = false;


            //variables to make player char valid
            boolean tokenGood = false;
            char tokens;
            int tokensAdded = 0;
            boolean tokenDuplicated = false;
            //loop to go to each player and get their token char
            for(int check = 0; check < numOfPlayers; check ++)
            {

                //while loop to check if the inputted token char is valid to ask player again
                while(!tokenGood)
                {
                    //gets char from player
                    System.out.println("Enter the character to represent player " + (check + 1));
                    tokens = reader.next().charAt(0);
                        //if it is a letter, make it capitalized ect: a -> A
                        if( 97 <= (int)tokens && (int)tokens <= 122)
                        {
                            int num = (int)tokens - 32;
                            char charA = (char) num;
                            tokens = charA;
                        }

                        //loop to check if the token has been chosen by a previous player
                    for(int look = 0; look < tokensAdded + 1; look++)
                    {
                        //if it matches a previous token chosen
                        if(tokens == playerChar[look])
                        {
                            //tell them token already chosen and need a new token
                            tokenDuplicated = true;
                            System.out.println(tokens + " is same as player " + look + " char");
                        }
                    }

                    if(tokenDuplicated)
                    {
                        System.out.println(tokens + " is already taken as a player token!");
                    }else{
                        playerChar[tokensAdded] = tokens;
                        tokenGood = true;
                        tokensAdded++;
                    }
                    tokenDuplicated = false;

                }
                tokenGood = false;

            }








            //loop to get correct row input
            while(!Acceptable)
            {

                System.out.println("How many rows should be on the board?");
                rows = reader.nextInt();
                //check to see if row is les than three
                if(rows < 3)
                {
                    System.out.println("Must have at least 3 rows.");
                }
                //checks to see if row is greater than 100
                else if(rows > 100)
                {
                    System.out.println("Can have at most 100 rows.");
                }else{
                    //end loop if 3 <= rows <= 100
                    Acceptable = true;
                }

            }


            Acceptable = false;

            while(!Acceptable)
            {
                System.out.println("How many columns should be on the board?");
                columns = reader.nextInt();
                //checks to see if columns is less than 3
                if(columns < 3)
                {
                    System.out.println("Must have at least 3 columns.");
                }
                //checks to see if columns is greater than 100
                else if(columns > 100)
                {
                    System.out.println("Can have at most 100 columns.");
                }else{
                    //ends loop is 3 <= columns <= 100
                    Acceptable = true;
                }

            }



            Acceptable = false;

            while(!Acceptable)
            {
                System.out.println("How many in a row to win?");
                needToWin = reader.nextInt();
                //checks to see if needToWin is less than 3
                if(needToWin < 3)
                {
                    System.out.println("Must have at least 3 in a row to win.");
                }
                //checks to see if row is greater than 25
                else if(needToWin > 25)
                {
                    System.out.println("Can have at most 25 in a row to win.");
                }else{
                    //end loop if 3 <= needToWin <= 25
                    Acceptable = true;
                }

            }




            //create a new board

            Scanner get = new Scanner(System.in);
            char choice = 's';
            boolean choose = false;
           while(!choose)
           {
               //get choice of the board game
               System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
               choice = get.nextLine().charAt(0);
               //if entered f or F make it fast board
               if(choice == 'F' || choice == 'f')
               {
                   choose = true;
                   choice = 'F';
               }
               //if entered m or M make it memory efficient board
               else if (choice == 'M' || choice == 'm')
               {
                   choose = true;
                   choice = 'M';
               }
               else{
                   System.out.println("Please choose M/m or F/f");
               }
           }


            IGameBoard board;
           //make board
            if(choice == 'F') {
                board = new GameBoard(columns, rows, needToWin);

            }else{
                    board = new GameBoardMem(columns, rows, needToWin);
                }



            int once = 0;
            boolean gameOver = false;//the current game boolean if it is over or not
            char player = playerChar[WhoseUp];//the player and the token
            WhoseUp = 0;
            boolean turnOver = false;// boolean for how the player and token switch
            char Awnser = 'm';// the input of the user to play again or quit


            //loop for game


            while (!gameOver) {

                    //if turn over is false player and token is X else it is O
                if(WhoseUp < numOfPlayers)
                {
                    player = playerChar[WhoseUp];
                    WhoseUp++;
                }
                if(WhoseUp >= numOfPlayers)
                {
                    WhoseUp = 0;
                }


                //if statement to print the first board game to start the game off
                if(once == 0) {
                    printBoardGame(board.toString());
                    once = -1;
                }


                //asks user where to place token and get the column number by input
                System.out.println("Player " + player + ", what column do you want to place your marker in?");
                int n = reader.nextInt();
                boolean columnEnter = false;


                //while the input is being checked to see if the n column within range and not full or loop to ask
                //question again
                while (!columnEnter) {
                    if (n < 0) {
                        System.out.println("Column cannot be less than 0 ");
                        System.out.println("Player " + player + ", what column do you want to place your marker in?");
                        n = reader.nextInt();
                    } else if (n > board.getNumColumns()-1) {
                        System.out.println("Column cannot be more than " + (board.getNumColumns() - 1));
                        System.out.println("Player " + player + ", what column do you want to place your marker in?");
                        n = reader.nextInt();
                    } else if (!board.checkIfFree(n)) {
                        System.out.println("Column is full");
                        System.out.println("Player " + player + ", what column do you want to place your marker in?");
                        n = reader.nextInt();
                    } else {
                        columnEnter = true;
                    }

                }

                //place token in the spot and print the board
                board.placeToken(player, n);
                printBoardGame(board.toString());


                boolean GameCheck = board.checkForWin(n);
                // if the game has just had a winnner print the winner slides and see if users want to play again
                if (GameCheck) {
                    System.out.println("Player " + player + " Won!");
                    System.out.println("Would you like to play again? Y/N");
                    //asks what user to do next end or new game
                    //checks to see if user inputted a n to leave y to start a new game and if niether, ask again

                    while(Awnser!= 'n' && Awnser!= 'N' && Awnser!= 'y' && Awnser!= 'Y')
                    {
                        Awnser = reader.next().charAt(0);
                        //if it is a letter, make it capitalized ect: a -> A
                        if( 97 <= (int)Awnser && (int)Awnser <= 122)
                        {
                            int num = (int)Awnser - 32;
                            char charA = (char) num;
                            Awnser = charA;
                        }


                        if (Awnser == 'N') {
                            gameOver = true;
                            Again = false;
                        } else if (Awnser == 'Y') {
                            gameOver = true;
                        } else {
                            System.out.println("Would you like to play again? Y/N");

                        }
                    }
                }
                //checks for a tie in the game and that it is over
                if(!GameCheck && board.checkTie())
                {
                    System.out.println("There is a tie");
                    System.out.println("Would you like to play again? Y/N");
                    while(Awnser!= 'n' && Awnser!= 'N' && Awnser!= 'y' && Awnser!= 'Y')
                    {
                        //asks what user to do next end or new game
                        //checks to see if user inputted a n to leave y to start a new game and if niether, ask again
                        Awnser = reader.next().charAt(0);
                        if (Awnser == 'N' || Awnser == 'n') {
                            gameOver = true;
                            Again = false;
                        } else if (Awnser == 'Y' || Awnser == 'y') {
                            gameOver = true;
                        } else {
                            System.out.println("Would you like to play again? Y/N");
                            Awnser = reader.next().charAt(0);
                        }
                    }
                }


            }// end of loop/ end of game possible to play again


        }// end all games

    }


    }

