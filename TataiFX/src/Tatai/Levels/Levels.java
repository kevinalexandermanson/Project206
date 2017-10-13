package Tatai.Levels;

public enum Levels {
	
	PractiseEasy("PractiseEasy"), PractiseHard("PractiseHard"), Addition("+"), Subtraction("-"), 
	Multiplication("*"), Division("/"), Random("Random"), RandomHard("RandomHard");
	
	private final String level;

	private Levels(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

}
