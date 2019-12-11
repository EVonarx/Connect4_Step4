package fr.evonarx.connect4;
import java.awt.*;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.event.*;

public class GridGraphical extends JFrame implements MouseListener, ActionListener {
	
	private static final long serialVersionUID = -1322362820347666830L;
	
	//The main panel
	JPanel mainPanel = new JPanel();
	
	//The grid panel
	JPanel gridPanel = new JPanel();
	
	//The tool bar
	JToolBar toolBar = new JToolBar();
	
	JButton jNew = new JButton("New Game");
	
	ImageIcon iOpen = new ImageIcon("open.gif");
	JButton jOpen = new JButton(iOpen);
	
	ImageIcon iSaveas = new ImageIcon("saveas.gif");
	JButton jSaveas = new JButton(iSaveas);
	
	ImageIcon iUndo = new ImageIcon("undo.gif");
	JButton jUndo = new JButton(iUndo);
	
	ImageIcon pionR = new ImageIcon("pionR.gif");
	ImageIcon pionV = new ImageIcon("pionV.gif");
	ImageIcon NoIcon = new ImageIcon("");
	
	//The status bar
	JLabel statusBar;
	
	
	public GridGraphical() {
	
		super("Connect 4");
		setSize(500, 400);
		setLocation(100, 100);
		
		mainPanel.setLayout(new BorderLayout());
		
		GridLayout gridLay;
		gridLay = new GridLayout(6, 7, 0, 0);
		//gridPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		gridPanel.setLayout(gridLay);
		gridPanel.setName("the grid panel");
		
		makeToolBar();
		mainPanel.add(toolBar, "North");
		
		
		makeCells();
		mainPanel.add(gridPanel, "Center");
		
		statusBar = new JLabel("It is player 1's turn", pionR, JLabel.LEFT);
		mainPanel.add(statusBar, "South");
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setContentPane(mainPanel);
		setVisible(true);
	}
		
	
	
	/* Adds the buttons in the toolbar and adds the ActionListeners to them*/
	public void makeToolBar() {
		
		jNew.addActionListener(this);
		jOpen.addActionListener(this);
		jSaveas.addActionListener(this);
		jUndo.addActionListener(this);
		
		jOpen.setText("Open");
		jSaveas.setText("Saveas");
		jUndo.setText("Undo");
		
		toolBar.add(jNew);
		toolBar.add(jOpen);
		toolBar.add(jSaveas);
		toolBar.add(jUndo);
	}
	
	/* Adds nbRow * nbCol cells in the GridGraphical object */	
	public void makeCells() {
		Cell c;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				c = new Cell(0, "name"+(5-i)+""+j); // create an  instance of Cell
				c.addMouseListener(this);
				gridPanel.add(c);
			}
		}	
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("mouseClicked");
		
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		Cell src = (Cell)evt.getSource();
		//System.out.println("mousePressed : Cell name = "+src.name);
		if (!Game.endOfTheGame) src.process();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouseReleased");
		
	}

	public void mouseEntered(MouseEvent evt) {
		//System.out.println("mouseEntered");
		Cell src = (Cell)evt.getSource();
		src.colorNextFreeCell(new Color(198, 198, 242));

	}

	
	@Override
	public void mouseExited(MouseEvent evt) {
		//System.out.println("mouseExited");
		Cell src = (Cell)evt.getSource();
		src.colorNextFreeCell(Color.white);
	}

	
	
	public void actionPerformed(ActionEvent actionEvent) {
		
		JButton src = (JButton)actionEvent.getSource();
		String TLabel = src.getText();
		//System.out.println("Label = "+TLabel);
		
		if (TLabel.equals("New Game")) { //OK
			Frame[] f = GridGraphical.getFrames();
			for (Frame frame : f) {
				frame.dispose();
			}
			GameGraphical j = new GameGraphical();
		}	
		
		
		if (TLabel.equals("Open")) {
			Cell c = (Cell)this.gridPanel.getComponent(0);
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
				c.open(fc.getSelectedFile());
		}
		
		if (TLabel.equals("Saveas")) {
			Cell c = (Cell)this.gridPanel.getComponent(0);
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
				c.save(fc.getSelectedFile());
		}
		
		if (TLabel.equals("Undo")) {  //OK
			Cell c = (Cell)this.gridPanel.getComponent(0); // On prend par exemple la 1ère case pour récupérer le Jeu
			c.undo();
		}
		
	
	}




}
