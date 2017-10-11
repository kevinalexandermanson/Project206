package Tatai.model;

public enum Levels {
	
	PractiseEasy("PractiseEasy"), PractiseHard("PractiseHard"), Addition("+"), Subtraction("-"), 
	Multiplication("*"), Division("/"), Random("Random");
	
	private final String level;

	private Levels(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

}
