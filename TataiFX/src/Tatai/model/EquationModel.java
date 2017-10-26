package Tatai.model;

import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import Tatai.Levels.Levels;

/** Deals with creating equations **/
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

		// If level is random, then pick a random operator
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
		
		// Initialize numbers
		int num1 = 999;
		int num2 = 2;
		
		// If operation is addition, loop until answer returned is < 99
		if (operation.equals(Levels.Addition.getLevel())) {
			while ((num1 + num2) > 99) {
				num1 = rand.nextInt(99) + 1; 
				num2 = rand.nextInt(99) + 1; 
			}
		}
		// If operation is subtraction, loop until answer is > 0
		else if (operation.equals(Levels.Subtraction.getLevel())) {
			num2 = 10000;
			while ((num1 - num2) < 0) {
				num1 = rand.nextInt(99) + 1; 
				num2 = rand.nextInt(99) + 1;
			}
		}
		// If operation is multiplication, pick two numbers between 1-10 to multiply
		else if (operation.equals(Levels.Multiplication.getLevel())) {
			num1 = rand.nextInt(9) + 1; 
			num2 = rand.nextInt(9) + 1; 

		}
		// If operation is division, loop until the division of the two numbers is 0
		else if (operation.equals(Levels.Division.getLevel())) {
			while ((num1 % num2) != 0) {
				num1 = rand.nextInt(9) + 1; 
				num2 = rand.nextInt(9) + 1; 
			}
		}
		
		// Form equation
		equation = num1 + " " + operation + " " + num2;
	}

	/**
	 * Method used for hard random numbers
	 */
	public void randNumHard() {
		
		int result = 0;
		StringBuilder builder = null;
		Random rand = new Random();

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");

		// While the answer is not between 1-99 contiue looping
		while ((result > 99) || (result < 1)) {

			builder = new StringBuilder();
			int randomNumber;
			boolean skip = false;
			
			// Produces a random number of operands in the expression
			int numOfOperand = (rand.nextInt(NUM_OF_OPERATIONS) + 2);
			

			// loop for the number of operands
			for (int i = 0 ; i < numOfOperand; i++) {

				// calculate a random number between 1 - 99
				randomNumber = rand.nextInt(99) + 1;

				// If the prevous operand was not "/" then append the number to the string
				if (skip == false) {
					builder.append(randomNumber);
				}
				skip = false;

				String operand = null;
				
				// Randomlly produce an operand
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

				// If the operand is not the last thing to append to the string do the following:
				if (i != numOfOperand - 1) {
					// If the operand is for divison
					if (operand.equals(Levels.Division.getLevel())) {

						String[] Res = builder.toString().split("[\\p{Punct}\\s]+");
						String numChar = Res[Res.length - 1];
						
						// append the operand
						builder.append(operand);
						
						// Finds the number before this operand
						int number = Integer.parseInt(numChar);
						
						int newNumber = 999;

						// loop until the division of the two numbers is 0
						while (number % newNumber != 0) {
							newNumber = rand.nextInt(9) + 1;
						}
						
						// Append new number to string
						builder.append(newNumber);

						
						
						// The rest of this block is to ensure there is no two division operands after each other
						// Otherwise the division screws up and can sometimes not equal an integer
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
						// Simply append operand if it is not for division
						builder.append(operand);
					}	

				}

			}
			
			// Calculate the result of the string to see if it is an integer between 1-99
			try {
				result = (int) engine.eval(builder.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(builder.toString());
				
				// FOR TESTING, THIS LINE ENSURES THE SYSTEM CLOSES INDICATING THAT THE EXPRESSION
				// IS NOT AN INTEGER BETWEEN 1 AND 99
				System.exit(1);
			}
		}
		String string = builder.toString();
		
		// Puts spaces in the string
		string = string.replaceAll("([^\\d-]?)(-?[\\d\\.]+)([^\\d]?)", "$1 $2 $3").replaceAll(" +", " ");
		equation = string.substring(1, string.length()-1);

	}
}
