package Tatai.Levels;

import Tatai.model.EquationModel;

public class RandomGenerator implements LevelInterface {
	
	private String label;
	private EquationModel model;
	
	public RandomGenerator() {
		model = new EquationModel();
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getEquation() {
		return model.getEquation();
	}
	

	public void calculate() {
		label = "Now Playing - [Practise Mode - Random]";
		model.newEquation(Levels.Random.getLevel());
	}
}
