package Tatai.model;

public enum Levels {
	
	PractiseEasy("PractiseEasy"), PractiseHard("PractiseHard"), Addition("Addition"), Subtraction("Subtraction"), 
	Multiplication("Multiplication"), Division("Division"), Random("Random");
	
	private final String level;

	private Levels(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

}
