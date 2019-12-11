package fr.evonarx.connect4;

public class Grid  {
	
	/* the members of the class Grid */
	char[][] grid = new char[6][7];
	
	/* a constructor */
	public Grid() {
		initGrid();
		
	}
	
	/* Initialisation of each cell of the grid with an empty character */
	public  void initGrid() {
		for (int i=0; i<6; i++) {
			for (int j=0; j<7; j++)
				grid[i][j] = ' ';
		}
		
	}

	/* Check if the grid is full */
	public boolean isGridFull() {
		int compt = 0;
		
		for (int i=0; i<6; i++) {
			for (int j=0; j<7; j++)
				if (grid[i][j] != ' ') compt++;
		}
		
		if (compt == 42) return true;
		
		return false;
	}
	
	/* Check if the grid is empty */
	public boolean isGridEmpty() {
		int compt = 0;
		
		for (int i=0; i<6; i++) {
			for (int j=0; j<7; j++)
				if (grid[i][j] == ' ') compt++;
		}
		
		if (compt == 42) return true;
		
		return false;
	}
	
	/* For the textual game, the range of the column given by the players is between 1 and 7 */
	public boolean isAnswerInRange(String s) {
		if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5") || s.equals("6") || s.equals("7"))
			return true;
		return false;
	}
	
	/* Put the token in the first empty row of a column */
	public boolean putTokenInTheColum(int column, char current_player ) {
		
		for (int i=0; i<6; i++) {
			if (grid [i][column] == ' ') {
				if (current_player == Player.PLAYER_ONE) grid [i][column] = '1';
				if (current_player == Player.PLAYER_TWO) grid [i][column] = '2';
				return true;
			}
		}
		 return false;	
		
	}
	
	/* Check if there are 4 slots in a row */
	public boolean isThereAWinner(char current_player) {
		boolean winner = false;
	
		winner = Are4SlotsConnect.Are4SlotsConnectHorizontally(grid, current_player);
		if (!winner) winner = Are4SlotsConnect.Are4SlotsConnectVertically(grid, current_player);
		if (!winner) winner = Are4SlotsConnect.Are4SlotsConnectInDiagonalUp(grid, current_player); // à tester plus profondément
		if (!winner) winner = Are4SlotsConnect.Are4SlotsConnectInDiagonalDown(grid, current_player);	// à tester plus profondément
			
		return winner;
	} 
	
	/* For the graphical implementation */
	public int GetTheNextFreeRowInThisColumn(int column) {
		
		int result = -1;
		for (int i=0; i<6; i++) {
			if (grid [i][column] == ' ') {
				result = i;
				break;
			}
		}
		
		 return result;	
		
	}
	
	/* for Undo function */
	public void DeleteThisToken(int row, int column)  {
		grid [row][column] = ' ';
	}
}
