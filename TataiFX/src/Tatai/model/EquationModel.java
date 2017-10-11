package Tatai.model;

import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EquationModel {
	
	private String equation;
	
	public EquationModel() {
		
	}
	
	public String getEquation() {
		return equation;
	}
	
	public void newEquation(String operation) {
		
		Random rand = new Random();
		
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
		if (operation.equals(Levels.Addition.getLevel())) {
			while ((num1 + num2) > 99) {
				num1 = rand.nextInt(99) + 1; 
				num2 = rand.nextInt(99) + 1; 
			}
		}
		else if (operation.equals(Levels.Multiplication.getLevel())) {
			while ((num1 * num2) > 99) {
				num1 = rand.nextInt(99) + 1; 
				num2 = rand.nextInt(99) + 1; 
			}
		}
		else if (operation.equals(Levels.Division.getLevel())) {
			while ((num1 / num2) > 99) {
				num1 = rand.nextInt(99) + 1; 
				num2 = rand.nextInt(99) + 1; 
			}
		}
		
		this.equation = num1 + " " + operation + " " + num2;
	}
	
	
}
