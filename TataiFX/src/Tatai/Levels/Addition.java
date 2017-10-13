package Tatai.Levels;

import Tatai.model.EquationModel;

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
		label = "Now Playing - [Practise Mode - Addition]";
		model.newEquation(Levels.Addition.getLevel());
	}
	
}
