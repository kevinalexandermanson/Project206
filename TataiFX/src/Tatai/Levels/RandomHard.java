package Tatai.Levels;

import Tatai.model.EquationModel;

/**
 * Class handles Random hard level
 *
 */
public class RandomHard implements LevelInterface{
	
	private String label;
	private EquationModel model;
	
	public RandomHard() {
		model = new EquationModel();
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getEquation() {
		return model.getEquation();
	}
	

	public void calculate() {
		//Calculates random hard level
		label = "Now Playing - [Maths Mode - Random Hard]";
		model.randNumHard();
	}
}
