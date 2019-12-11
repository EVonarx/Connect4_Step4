package fr.evonarx.connect4;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;


/* For textual game */
public class Game {
	
	/* the members of the class Game */
	public static Grid gameGrid; //global variable Grid object
	public static Player player; //global variable Player object
	public static ArrayList gameHistory; //global variable ArrayList object
	
	public static boolean endOfTheGame= false; //global variable endOfTheGame
	public static boolean changePlayer = false; //global variable change_player
	
	public Game() {
		gameGrid = new Grid(); // create an  instance of the Grid object
		player = new Player(); // create an  instance of Player object
		gameHistory = new ArrayList(); // create an  instance of an ArrayList
	}

	/* Only for the textual grid 				*/
	/* Print the textual grid of the game 		*/
	public void printGrid(Grid grid) {
		System.out.println();
		for (int i=0; i<6; i++) 
			System.out.println(6-i + "  [  |" + gameGrid.grid[5-i][0] + "|  |" + gameGrid.grid[5-i][1] + "|  |" + gameGrid.grid[5-i][2] + "|  |" + gameGrid.grid[5-i][3]+ "|  |" + gameGrid.grid[5-i][4] + "|  |" + gameGrid.grid[5-i][5] + "|  |" + gameGrid.grid[5-i][6]+ "|  ]");
		System.out.println("       1    2    3    4    5    6    7 ");
	
	}
	
	/* Only for the textual game														*/
	/* Manage every possibilities of the textual input until the end of the game 		*/
	public void go() {
		
		printGrid(gameGrid); // draw the textual game in the console
		
		boolean EndOfTheGame= false;
		boolean change_player = false;
		
		
		while (!EndOfTheGame) {
			if (!gameGrid.isThereAWinner(player.getCurrent_player())) {
				if (!gameGrid.isGridFull()) {
					if (change_player == true ) player.changePlayer(player.getCurrent_player());
					change_player = play(player.getCurrent_player());
				}
				else {
						EndOfTheGame= true;
						System.out.println("Draw...");
				}
			}
				
			else {
				EndOfTheGame= true;
				System.out.println("There is a winner...It is player "+ player.getCurrent_player());
			}
		}
		
		
	}
	
	/* Only for the textual game 																				*/
	/* Read the player's choice, put the token in the column if possible and refresh the printing of the grid	*/
	private boolean play(char current_player) {
		 Scanner input = new Scanner(System.in);
		 String answer ="";
		
		 while (!gameGrid.isAnswerInRange(answer))
		    {
			    System.out.print("Player " + current_player + " : Enter the column number (1 to 7) => ");
			    answer = input.next();
			    answer.toLowerCase();
		    }
		 
		 int a = Integer.parseInt(answer);
		 a=a-1;
		
		 
		 if (gameGrid.putTokenInTheColum(a, current_player)) {
			 printGrid(gameGrid);
			 return true;
			 }
		 else {
			 System.out.println("This column is full !!!!");
			 return false;
		 }
			 
	}
	
	
	/* Run the textual game */
	public static void main(String[] args) {
		Game j = new Game();
		j.go();
	}

}
