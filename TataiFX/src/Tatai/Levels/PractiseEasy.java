package Tatai.Levels;

import java.util.Random;

/**
 * Class handles practice easy level
 */
public class PractiseEasy implements LevelInterface {
	
	private String label;
	private String number;
	
	public PractiseEasy() {
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getEquation() {
		return number;
	}
	

	public void calculate() {
		//Calculates random number for easy mode between 1 and 10
		label = "Now Playing - [Practise Mode - Easy]";
		number = Integer.toString(randomNumber());
	}
	
	private int randomNumber() {

		int randomNumber = 0;
		Random rand = new Random();
		
		randomNumber = rand.nextInt(9) + 1; //Range 1-9
		
		return randomNumber;
	}
}
