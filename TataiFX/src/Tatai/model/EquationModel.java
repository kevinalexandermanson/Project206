package Tatai.model;

import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import Tatai.Levels.Levels;

public class EquationModel {
	
	private String equation;
	
	public EquationModel() {
		
	}
	
	public String getEquation() {
		return equation;
	}
	
	public void newEquation(String operation) {
		Random rand = new Random();
		
		if (operation.equals(Levels.Random.getLevel())) {
			int temp =  rand.nextInt(4); 
			if (temp == 0) {
				operation = Levels.Addition.getLevel();
			}
			else if (temp == 1) {
				operation = Levels.Subtraction.getLevel();
			}
			else if (temp == 2) {
				operation = Levels.Multiplication.getLevel();
			}
			else if (temp == 3) {
				operation = Levels.Division.getLevel();
			}
		}

		int num1 = 1000;
		int num2 = 2;
		
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = 0;
        
        try {
        	result = engine.eval(num1 + operation + num2);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		if (operation.equals(Levels.Addition.getLevel()) || operation.equals(Levels.Subtraction.getLevel())) {
			while ((num1 + num2) > 99) {
				num1 = rand.nextInt(99) + 1; 
				num2 = rand.nextInt(99) + 1; 
			}
		}
		else if (operation.equals(Levels.Multiplication.getLevel())) {
			num1 = rand.nextInt(9) + 1; 
			num2 = rand.nextInt(9) + 1; 
			
		}
		else if (operation.equals(Levels.Division.getLevel())) {
			num1 = 999;
			while ((num1 % num2) != 0) {
				num1 = rand.nextInt(9) + 1; 
				num2 = rand.nextInt(9) + 1; 
			}
		}
		
		this.equation = num1 + " " + operation + " " + num2;
	}
	
	
}
