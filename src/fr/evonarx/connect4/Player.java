package fr.evonarx.connect4;

public class Player {

	static final char PLAYER_ONE = '1';
	static final char PLAYER_TWO = '2';
	private char current_player = PLAYER_ONE;
	
	public char getCurrent_player() {
		return current_player;
	}

	public void Player() {
		setCurrent_player(PLAYER_ONE);
	}
	
	public void setCurrent_player(char current_player) {
		this.current_player = current_player;
	}


	public void changePlayer(char current_player) {
		
		if (current_player==PLAYER_ONE) {
			current_player=PLAYER_TWO;
		}
		else 
			current_player=PLAYER_ONE;
		
		//System.out.println("I changed the current player, now it is player  "+ current_player + " turn");
		setCurrent_player(current_player);
		
	}
}
