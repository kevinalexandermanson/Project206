package Tatai.Levels;

import Tatai.model.EquationModel;

/**
 * Class handles Addition level
 */
public class Addition implements LevelInterface {

	private String label;
	private EquationModel model;
	
	public Addition() {
		model = new EquationModel();
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getEquation() {
		return model.getEquation();
	}
	

	public void calculate() {
		// Calculates level as Addition
		label = "Now Playing - [Maths Mode - Addition]";
		model.newEquation(Levels.Addition.getLevel());
	}
	
}
