package Tatai.Levels;

import Tatai.model.EquationModel;

/**
 * Class handles Subtraction level
 *
 */
public class Subtraction implements LevelInterface {

	private EquationModel model;
	private String label;
	
	public Subtraction() {
		model = new EquationModel();
	}
	
	public void calculate() {
		//Calculates subtraction question
		label = "Now Playing - [Practise Mode - Subtraction]";
		model.newEquation(Levels.Subtraction.getLevel());
	}

	public String getEquation() {
		return model.getEquation();
	}

	public String getLabel() {
		return label;
	}
	
}
