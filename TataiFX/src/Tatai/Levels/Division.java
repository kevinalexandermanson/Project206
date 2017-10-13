package Tatai.Levels;

import Tatai.model.EquationModel;

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
		label = "Now Playing - [Practise Mode - Division]";
		model.newEquation(Levels.Division.getLevel());
	}
}
