package Tatai.Levels;

import Tatai.model.EquationModel;

/**
 * Class handles RandomEasy level
 *
 */
public class RandomEasy implements LevelInterface {
	
	private String label;
	private EquationModel model;
	
	public RandomEasy() {
		model = new EquationModel();
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getEquation() {
		return model.getEquation();
	}
	

	public void calculate() {
		//Calulates random easy level question
		label = "Now Playing - [Practise Mode - Random Easy]";
		model.newEquation(Levels.Random.getLevel());
	}
}
