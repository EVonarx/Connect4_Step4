package fr.evonarx.connect4;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;

public class GameGraphical extends Game {
	
	public static GridGraphical gridG; //global variable graphical object
	
	
	public GameGraphical() {
	
		gridG = new GridGraphical(); //create an instance of the graphical game
	
		
	}

	public static void main(String[] args) {
		GameGraphical j = new GameGraphical();
	}
	

}
