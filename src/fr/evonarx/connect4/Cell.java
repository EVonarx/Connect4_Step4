package fr.evonarx.connect4;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Cell extends JPanel {
	
	private static final long serialVersionUID = 36172644784724253L;

	//a cell is defined by its name, its value and its background color;
	
	String name; //name + row number + column number
	//name50 name51 name52 name53 name54 name55 name56
	//name40 name41 name42 name43 name44 name45 name46
	//name30 name31 name32 name33 name34 name35 name36
	//name20 name21 name22 name23 name24 name25 name26
	//name10 name11 name12 name13 name14 name15 name16
	//name00 name01 name02 name03 name04 name05 name06
	
	int value; //1 = player 1 ; 2 = player 2
	Color bgCol;
	
	// A constructor
	public Cell() {
		super();
	}
	
	// Another constructor
	public Cell(int value, String name) {
		super();
	
		this.value = value;
		this.bgCol = Color.white;
		
		this.name = name;
		
	}
	
	// Another constructor
	public Cell(String name) {
		super();
		this.bgCol = Color.white;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	
	public void changeVal(int value) {
		this.value = value;
		this.bgCol = Color.white; // Pour bien confirmer le fond blanc de la case
		repaint();
	}
	
	public void changeBg(Color color) {
		this.bgCol = color;
		repaint();
	}
	
	
	/*
	 	Who calls paintComponent ?
		When you subclass JComponent or JPanel to draw graphics, override the paintComponent() method. This method is called because the user did something with the user interface that required redrawing, or your code has explicitly requested that it be redrawn.

	   	Called automatically when it becomes visible
		When a window becomes visible (uncovered or deminimized) or is resized, the "system" automatically calls the paintComponent() method for all areas of the screen that have to be redrawn.
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics comp) {
		Graphics2D comp2D = (Graphics2D)comp;
		
		 comp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 RenderingHints.VALUE_ANTIALIAS_ON); // Pour l'antialias
		 
	
		//the background color
		comp2D.setColor(bgCol);
		
		int Width = getWidth();
		int Height = getHeight();
		//System.out.println("Width = "+Width+" Height = "+Height);
		comp2D.fillRect(0, 0, getWidth(), getHeight());
		
		//the borders of the rectangle
		comp2D.setColor(Color.black);
		comp2D.drawRect(0, 0, getWidth(), getHeight());
               
		if (value != 0) {
			if (value == 1)
				comp2D.setColor(Color.red);
			else
				comp2D.setColor(Color.green);
			
			//Draws the outline of a circular or elliptical arc covering the specified rectangle.
			comp2D.fillOval(3, 3, getWidth() - 4, getHeight() - 4);
		}
	}

	
	/* Only for the graphical grid 														*/
	/* Manage every possibilities of the graphical input until the end of the game 		*/
	public void process()
	{
		JLabel jl = GameGraphical.gridG.statusBar;
		jl.setHorizontalAlignment(JLabel.LEFT);
		
		String cellName = this.getName(); //get the name of the cell
		int column = extractColumnNbFromACellName(cellName); // extract the column number
		
		int row = Game.gameGrid.GetTheNextFreeRowInThisColumn(column); //get the next free row in this column
		if (row != -1) {
			
			Game.changePlayer = Game.gameGrid.putTokenInTheColum(column, Game.player.getCurrent_player());
			
			int size = Game.gameHistory.size();
			Game.gameHistory.add(new Shot(size+1,row,column));
			
			//int lastShotIndex = Game.gameHistory.getLastShotIndex()+1;
			//int lastShotIndex = Game.gameHistory.getLastShotIndex();
			//Game.gameHistory.getArrayOfShots()[Game.gameHistory.getLastShotIndex()].setShotIndex(lastShotIndex);
			
			//Game.gameHistory.getArrayOfShots()[Game.gameHistory.getLastShotIndex()].setRow(row);
			//Game.gameHistory.getArrayOfShots()[Game.gameHistory.getLastShotIndex()].setColumn(column);
			
			String nameToFind = "name"+(row)+""+column;	
			
			JPanel jp = GameGraphical.gridG.gridPanel;
			//JPanel jp = (JPanel)this.getParent();
			Cell resultCell = this.getCellByName(jp, nameToFind);
					
			resultCell.changeBg(Color.white);
			char c = Game.player.getCurrent_player();
			int val = 0;
			if (c == '1') val = 1;
			else val = 2;
			
			resultCell.changeVal(val);
			//repaint();
			//is there a winner ?
			//System.out.println("process - current player = "+Game.player.getCurrent_player());
			
			if (!Game.endOfTheGame) {
				if (!Game.gameGrid.isThereAWinner(Game.player.getCurrent_player())) {
					if (!Game.gameGrid.isGridFull()) {
						if (Game.changePlayer == true ) {
							Game.player.changePlayer(Game.player.getCurrent_player());
							
							//System.out.println("process - current player changed = "+Game.player.getCurrent_player() );
							updateLabel();
						}
						
					}
					else {
							Game.endOfTheGame= true;
							jl.setIcon(GameGraphical.gridG.NoIcon);
							jl.setText("DRAW...");
					}
				}
					
				else {
					Game.endOfTheGame= true;
					jl.setIcon(GameGraphical.gridG.NoIcon);
					jl.setText("WINNER : PLAYER "+Game.player.getCurrent_player());
					Game.player.changePlayer(Game.player.getCurrent_player()); //for the undo function
				}
			}
			
			
		} else {
			jl.setIcon(GameGraphical.gridG.NoIcon);
			jl.setText("COLUMN "+(column+1)+" IS FULL : PLAYER "+ Game.player.getCurrent_player()+ " HAS TO PLAY");
		}
			
	}
	
	/* Only for the graphical grid 												*/
	/* Color the next free cell of a column with the color given in parameter 	*/
	public void colorNextFreeCell(Color color)
	{
		
		String cellName = this.getName();
		int column = extractColumnNbFromACellName(cellName);
		
		// Where is the next free cell in this column ?
		int result = Game.gameGrid.GetTheNextFreeRowInThisColumn(column);
			
		if (result != -1) {
			
			String nameToFind = "name"+result+""+column;
			JPanel jp = (JPanel)this.getParent();
			Cell resultCell = getCellByName(jp, nameToFind);
			
			resultCell.changeBg(color);
			//repaint();
		}	
	}
	
	/* Only for the graphical grid 								*/
	/* Get the object cell by the name given in parameter 		*/
	public Cell getCellByName(JPanel jp, String name) {
		for(Component child: jp.getComponents()){
		    if(child instanceof Cell){
		    	Cell cellt = (Cell) child;
		    	if (cellt.name.equals(name)) return cellt;
		    }
		}
		return null;
		
	}
	
	/* Only for the graphical grid 					*/
	/* Extract the column number from a cell name 	*/
	public int extractColumnNbFromACellName(String name) { //name(= 4 letters) + row number + column number => example : name00
		String s = name.substring(5, 6);
		return (Integer.parseInt(s));		
	}
	
	public void updateLabel() {
		JLabel jl = GameGraphical.gridG.statusBar;
		jl.setHorizontalAlignment(JLabel.LEFT);
		if (Game.player.getCurrent_player() == '1')
		{
			jl.setText("It is player 1's turn");
			jl.setIcon(GameGraphical.gridG.pionR);
		}		
		else {
			jl.setText("It is player 2's turn");
			jl.setIcon(GameGraphical.gridG.pionV);
			
		}
		jl.updateUI();
		//repaint();
	}
	
	
	public void undo() {
				
		int size = Game.gameHistory.size();
		
		if (size != 0) {
			
			/*
			Shot s1;
			System.out.println("list of played shots ");
			for (int i=0; i<size; i++) {
				s1 = (Shot) Game.gameHistory1.get(i);
				System.out.println("shot n°"+s1.getShotIndex()+" : row = "+s1.getRow()+" column = "+s1.getColumn());
			}
			*/
			
			JPanel jp = (JPanel)this.getParent();
			
			Shot s = (Shot)Game.gameHistory.get(size-1);
			//System.out.println("shot to delete => n°"+s.getShotIndex()+" : row = "+s.getRow()+" column = "+s.getColumn());
			int row = s.getRow();
			int column = s.getColumn();
			
			String nameToFind = "name"+row+""+column;	
			
			Game.gameHistory.remove(size-1); //delete the shot in the history list
			Game.gameGrid.DeleteThisToken(row, column); //delete the token in the game grid
			Game.player.changePlayer(Game.player.getCurrent_player()); //the current player is changed
			updateLabel(); //the status has to be updated
			Game.endOfTheGame = false; //when a token is deleted, the game isn't over yet
			
			Cell resultCell = getCellByName(jp, nameToFind);
			resultCell.changeVal(0);
			//repaint();
		}
		
	}
	
	
	
	public void save(File file) {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			out.write("Connect 4  Eric Vonarx\n");  // Header
			
			for (int i = 0; i < Game.gameHistory.size(); i++) {
				Shot s = (Shot)Game.gameHistory.get(i);
				out.write(s.getShotIndex()+" "+s.getRow()+" "+s.getColumn()+"\n");
			}
			
			out.close();
		} catch (IOException e) {
			
		}
	}
	
	public void open(File file) {
	
		try {
			FileReader fr = new FileReader(file);
			BufferedReader out = new BufferedReader(fr);
			String line = out.readLine();
			if (line.equals("Connect 4  Eric Vonarx")) {  // validity test :-)
				System.out.println("First line => OK");
				
				/* Clearing and initialising of the game context */
				Game.player.setCurrent_player('1');
				Game.gameHistory.clear();
				Game.gameGrid.initGrid();
				for (int i=0; i<42; i++)
				{
					Cell cell = (Cell)GameGraphical.gridG.gridPanel.getComponent(i);
					cell.changeVal(0);
				}
				/* end Clearing and initialising of the game context */
				
				while (true) {
					/* I read a line of the file */
					line = out.readLine();
					if (line != null) {
						System.out.println("Line = "+line);
						StringTokenizer s = new StringTokenizer(line);
						int paramShotNumber = Integer.parseInt(s.nextToken());
						//System.out.println("    ShotNumber = "+paramShotNumber);
						int paramRow = Integer.parseInt(s.nextToken());
						//System.out.println("    Row = "+paramRow);
						int paramColumn = Integer.parseInt(s.nextToken());
						//System.out.println("    Column = "+paramColumn);
						
						/* I create a new cell */
						Cell resultCell = new Cell ("name"+paramRow+""+paramColumn);
						/* I update this new cell */
						resultCell.changeVal(1);
						/* I call the method that processes the move */
						resultCell.process();
					}
					else break;
				}
				out.close();
			}
		else {
			JLabel jl = GameGraphical.gridG.statusBar;
			jl.setHorizontalAlignment(JLabel.LEFT);
			jl.setText("IT ISN'T A VALID CONNECT 4 FILE");
			jl.setIcon(GameGraphical.gridG.pionR);
			jl.updateUI();
		}
			
		
		} catch (IOException e) {
		
		}
	
	}
	
}
