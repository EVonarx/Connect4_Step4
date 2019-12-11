package fr.evonarx.connect4;

public class Are4SlotsConnect {

	
	//HORIZONTAL
		public static boolean Are4SlotsConnectHorizontally(char[][]  grid, char current_player) {
		
			int compt=0;
			int StartColumn=0;
			
			for (int i=0; i<6; i++) {
				//System.out.println("ligne = "+(i+1));
				
				StartColumn=0;
				
				for (int j=StartColumn; j<StartColumn+4; j++) {
					//System.out.println("I check the position" +"["+(i+1)+"]"+"["+(j+1)+"]"+ " => there is : " + GridTextual[i][j] +" and I check the character = "+current_player);
					
					if (grid[i][j] == current_player) {
						compt++;
						//System.out.println("From the column " +(StartColumn+1)+ " At the moment the counter = "+compt);
						if (compt == 4) 
							{
							//System.out.println("The player "+ current_player + " won");
							return true;
							}
					} else {
						compt=0;
						StartColumn=j+1;
						//System.out.println("A la position" +"["+(i+1)+"]"+"["+(j+1)+"]"+ " => il y a : " + GridTextual[i][j] + " donc je mets le compteur à 0 et je traite la colonne suivante, la colonne : "+ (StartColumn+1));
						
						if (StartColumn >= 4) {
							//System.out.println("Supérieur ou égal à la colonne 5 => Je vais à la ligne suivante");
							break;
						}
					}
					
				} // end of the "for" instruction for the columns
				
			} // end of the "for" instruction for a raw
			
			//System.out.println("There are not 4 slots connected horizontaly");
			return false;
		}

		
		//VERTICAL
		public static boolean Are4SlotsConnectVertically(char[][]  grid, char current_player) {
			
			int compt=0;
			int StartLine=0;
			
			for (int j=0; j<7; j++) {
				//System.out.println("colonne = "+(j+1));
				
				StartLine=0;
				
				for (int i=StartLine; i<StartLine+4; i++) {
					//System.out.println("Je traite la position" +"["+(i+1)+"]"+"["+(j+1)+"]"+ " => il y a : " + GridTextual[i][j] +" et je traite le caractère = "+current_player);
					
					if (grid[i][j] == current_player) {
						compt++;
						//System.out.println("A partir de la ligne " +(StartLine+1)+ " pour l'instant compteur = "+compt);
						if (compt == 4) 
							{
							//System.out.println("C'est le joueur "+ current_player + " qui a gagné");
							return true;
							}
					} else {
						compt=0;
						StartLine=i+1;
						//System.out.println("A la position" +"["+(i+1)+"]"+"["+(j+1)+"]"+ " => il y a : " + GridTextual[i][j] + " donc je mets le compteur à 0 et je traite la ligne suivante, la ligne : "+ (StartLine+1));
						
						if (StartLine >= 3) {
							//System.out.println("Supérieur ou égal à la ligne 4 => Je vais à la colonne suivante");
							break;
						}
					}
					
				} // end of the "for" instruction for the lines
				
			} // end of the "for" instruction for a column
			
			//System.out.println("There are not 4 slots connected verticaly");	
			return false;
		}
		
		
		//DIAGONAL_UP
		public static boolean Are4SlotsConnectInDiagonalUp(char[][]  grid, char current_player) {
			//start cells => 12 cells
			// GridTextual[5][0]; GridTextual[5][1]; GridTextual[5][2]; GridTextual[5][3]; GridTextual[5][4]; GridTextual[5][5]; GridTextual[5][6]
			// GridTextual[4][0]; GridTextual[4][1]; GridTextual[4][2]; GridTextual[4][3]; GridTextual[4][4]; GridTextual[4][5]; GridTextual[4][6]
			// GridTextual[3][0]; GridTextual[3][1]; GridTextual[3][2]; GridTextual[3][3]; GridTextual[3][4]; GridTextual[3][5]; GridTextual[3][6]
			// *GridTextual[2][0]; *GridTextual[2][1]; *GridTextual[2][2]; *GridTextual[2][3]; GridTextual[2][4]; GridTextual[2][5]; GridTextual[2][6]
			// *GridTextual[1][0]; *GridTextual[1][1]; *GridTextual[1][2]; *GridTextual[1][3]; GridTextual[1][4]; GridTextual[1][5]; GridTextual[1][6]
			// *GridTextual[0][0]; *GridTextual[0][1]; *GridTextual[0][2]; *GridTextual[0][3]; GridTextual[0][4]; GridTextual[0][5]; GridTextual[0][6]
			
			int compt = 0;
			
			int i_initial =0;
			int j_initial= 0; 
			int i_temp = i_initial;
			int j_temp = j_initial;
			
			while (true) {
				if (grid[i_temp][j_temp] == current_player) {
					compt++;
					i_temp++;
					j_temp++;
					//System.out.println("A partir de la ligne " +(StartLine+1)+ " pour l'instant compteur = "+compt);
					if (compt == 4) {
						//System.out.println("C'est le joueur "+ current_player + " qui a gagné");
						
						return true;
					}
				} else {
					compt=0;
					i_initial++;
					i_temp = i_initial;
					j_temp= j_initial;
								
								
					//System.out.println("A la position" +"["+(i+1)+"]"+"["+(j+1)+"]"+ " => il y a : " + GridTextual[i][j] + " donc je mets le compteur à 0 et je traite la ligne suivante, la ligne : "+ (StartLine+1));
								
					if (i_temp >= 3) {
						//System.out.println("Supérieur ou égal à la ligne 4 => Je vais à la colonne suivante");
						i_initial =0;
						i_temp = i_initial;
						j_initial++; 
						j_temp= j_initial;
						
						if (j_temp >= 4) break;
						
					}
										
				}
			}
			
			//System.out.println("There are not 4 slots connected in a diagonal going up (from left to right)");
			return false;	
		}
			
			
		
		//DIAGONAL_DOWN
		public static boolean Are4SlotsConnectInDiagonalDown(char[][]  grid, char current_player) {
			//start cells => 12 cells
			// *GridTextual[5][0]; *GridTextual[5][1]; *GridTextual[5][2]; *GridTextual[5][3]; GridTextual[5][4]; GridTextual[5][5]; GridTextual[5][6]
			// *GridTextual[4][0]; *GridTextual[4][1]; *GridTextual[4][2]; *GridTextual[4][3]; GridTextual[4][4]; GridTextual[4][5]; GridTextual[4][6]
			// *GridTextual[3][0]; *GridTextual[3][1]; *GridTextual[3][2]; *GridTextual[3][3]; GridTextual[3][4]; GridTextual[3][5]; GridTextual[3][6]
			// GridTextual[2][0]; GridTextual[2][1]; GridTextual[2][2]; GridTextual[2][3]; GridTextual[2][4]; GridTextual[2][5]; GridTextual[2][6]
			// GridTextual[1][0]; GridTextual[1][1]; GridTextual[1][2]; GridTextual[1][3]; GridTextual[1][4]; GridTextual[1][5]; GridTextual[1][6]
			// GridTextual[0][0]; GridTextual[0][1]; GridTextual[0][2]; GridTextual[0][3]; GridTextual[0][4]; GridTextual[0][5]; GridTextual[0][6]
			int compt = 0;
			
			int i_initial =5;
			int j_initial= 0; 
			int i_temp = i_initial;
			int j_temp = j_initial;
			
			
			while (true) {
				if (grid[i_temp][j_temp] == current_player) {
					compt++;
					i_temp--;
					j_temp++;
					//System.out.println("A partir de la ligne " +(StartLine+1)+ " pour l'instant compteur = "+compt);
					if (compt == 4) {
						//System.out.println("C'est le joueur "+ current_player + " qui a gagné");
						return true;
					}
				} else {
					compt=0;
					i_initial--;
					i_temp = i_initial;
					j_temp= j_initial;
								
								
					//System.out.println("A la position" +"["+(i+1)+"]"+"["+(j+1)+"]"+ " => il y a : " + GridTextual[i][j] + " donc je mets le compteur à 0 et je traite la ligne suivante, la ligne : "+ (StartLine+1));
								
					if (i_temp <= 2) {
						//System.out.println("Supérieur ou égal à la ligne 4 => Je vais à la colonne suivante");
						i_initial =5;
						i_temp = i_initial;
						j_initial++; 
						j_temp= j_initial;
						
						if (j_temp >= 4) break;
						
					}
										
				}
			}
			
			//System.out.println("There are not 4 slots connected in a diagonal going down (from left to right)");
			return false;
		}
	
}
