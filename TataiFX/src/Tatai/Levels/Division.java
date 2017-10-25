package Tatai.Levels;

import Tatai.model.EquationModel;

/**
 * Class handles division level
 */
public class Division implements LevelInterface {
	
	private String label;
	private EquationModel model;
	
	public Division() {
		model = new EquationModel();
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getEquation() {
		return model.getEquation();
	}
	

	public void calculate() {
		// Calculates level as division
		label = "Now Playing - [Maths Mode - Division]";
		model.newEquation(Levels.Division.getLevel());
	}
}
