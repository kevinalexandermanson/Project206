package Tatai.Levels;

import Tatai.model.EquationModel;

/**
 * Class handles Multiplication level
 */
public class Multiplication implements LevelInterface {
	
	private String label;
	private EquationModel model;
	
	public Multiplication() {
		model = new EquationModel();
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getEquation() {
		return model.getEquation();
	}
	

	public void calculate() {
		// Calculates level as Multiplication
		label = "Now Playing - [Practise Mode - Multiplication]";
		model.newEquation(Levels.Multiplication.getLevel());
	}
}
