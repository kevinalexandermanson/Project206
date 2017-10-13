package Tatai.model;

import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import Tatai.Levels.Levels;

public class EquationModel {

	private String equation;
	private final static int NUM_OF_OPERATIONS = 4;

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

		int num1 = 999;
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
			while ((num1 % num2) != 0) {
				num1 = rand.nextInt(9) + 1; 
				num2 = rand.nextInt(9) + 1; 
			}
		}

		equation = num1 + " " + operation + " " + num2;
	}

	public void randNumHard() {
		
		int result = 0;
		StringBuilder builder = null;
		Random rand = new Random();

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");

		while ((result > 99) || (result < 1)) {

			builder = new StringBuilder();
			int randomNumber;
			int numOfOperand = (rand.nextInt(NUM_OF_OPERATIONS) + 2);
			boolean skip = false;

			for (int i = 0 ; i < numOfOperand; i++){

				randomNumber = rand.nextInt(99) + 1;

				if (skip == false) {
					builder.append(randomNumber);
				}
				skip = false;

				String operand = null;
				int randomOperand = rand.nextInt(4);

				if (randomOperand == 0) {
					operand = Levels.Addition.getLevel();
				}
				else if (randomOperand == 1) {
					operand = Levels.Subtraction.getLevel();
				}
				else if (randomOperand == 2) {
					operand = Levels.Multiplication.getLevel();
				}
				else if (randomOperand == 3) {
					operand = Levels.Division.getLevel();
				}


				if (i != numOfOperand - 1) {
					if (operand.equals(Levels.Division.getLevel())) {

						String[] Res = builder.toString().split("[\\p{Punct}\\s]+");

						String numChar = Res[Res.length - 1];
						builder.append(operand);

						int number = Integer.parseInt(numChar);
						int newNumber = 999;

						while (number % newNumber != 0) {
							newNumber = rand.nextInt(9) + 1;
						}

						builder.append(newNumber);

						randomOperand = rand.nextInt(3);

						if (randomOperand == 0) {
							operand = Levels.Addition.getLevel();
						}
						else if (randomOperand == 1) {
							operand = Levels.Subtraction.getLevel();
						}
						else if (randomOperand == 2) {
							operand = Levels.Multiplication.getLevel();
						}
						
						builder.append(operand);
					}
					else {
						builder.append(operand);
					}	

				}

			}
			try {
				result = (int) engine.eval(builder.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(builder.toString());
			}
		}
		String string = builder.toString();
		
		string = string.replaceAll("([^\\d-]?)(-?[\\d\\.]+)([^\\d]?)", "$1 $2 $3").replaceAll(" +", " ");
		equation = string.substring(1, string.length()-1);

	}
}
