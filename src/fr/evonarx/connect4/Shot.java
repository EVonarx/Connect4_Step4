package fr.evonarx.connect4;

public class Shot {
	
	/* the members of the class Shot */
	private int shotIndex; //shot index
	private int row;		//row played
	private int column;		//column played
	
	
	public Shot(int shotIndex, int row, int column) {
		this.shotIndex = shotIndex;
		this.row = row;
		this.column = column;
		
	}
	
	public Shot() {
		setShotIndex(0);
		setRow(0);
		setColumn(0);
	}
	
	public int getShotIndex() {
		return shotIndex;
	}
	
	public void setShotIndex(int shotIndex) {
		this.shotIndex = shotIndex;
	}
	
	public void incShotIndex() {
		shotIndex++;

	}
	

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void printShot() {
		System.out.println("shotIndex ="+getShotIndex());
		System.out.println("row ="+getRow());
		System.out.println("column ="+getColumn());
	}
	
}


