package Tatai.Levels;

import java.util.Random;

/**
 * Class handles PractiseHard level
 */
public class PractiseHard implements LevelInterface{
	
	private String label;
	private String number;
	
	public PractiseHard() {
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getEquation() {
		return number;
	}
	

	public void calculate() {
		//Calculates random number for hard mode between 1 and 100
		label = "Now Playing - [Practise Mode - Hard]";
		number = Integer.toString(randomNumber());
	}
	
	private int randomNumber() {

		int randomNumber = 0;
		Random rand = new Random();
		
		randomNumber = rand.nextInt(99) + 1; //Range is now 1-9, as specified, rather than 0-9
		
		return randomNumber;
	}
}
