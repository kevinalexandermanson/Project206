package Tatai.Levels;

import Tatai.model.EquationModel;

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
		label = "Now Playing - [Practise Mode - Multiplication]";
		model.newEquation(Levels.Multiplication.getLevel());
	}
}
