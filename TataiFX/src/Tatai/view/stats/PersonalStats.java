package Tatai.view.stats;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import Tatai.Levels.Levels;

//@XmlRootElement(name="personalStats")
public class PersonalStats {
	private int BestAdd = 0;
	private int LastAdd = 0;
	private int GamesPlayedAdd = 0;
	private double MeanAdd = 0;
	private int[] Last10Add;

	private int BestSub = 0;
	private int LastSub = 0;
	private int GamesPlayedSub = 0;
	private double MeanSub = 0;
	private int[] Last10Sub;

	private int BestMul = 0;
	private int LastMul = 0;
	private int GamesPlayedMul = 0;
	private double MeanMul = 0;
	private int[] Last10Mul;

	private int BestDiv = 0;
	private int LastDiv = 0;
	private int GamesPlayedDiv = 0;
	private double MeanDiv = 0;
	private int[] Last10Div;
	
	private int BestPracE = 0;
	private int LastPracE = 0;
	private int GamesPlayedPracE = 0;
	private double MeanPracE = 0;
	private int[] Last10PracE;

	private int BestPracH = 0;
	private int LastPracH = 0;
	private int GamesPlayedPracH = 0;
	private double MeanPracH = 0;
	private int[] Last10PracH;
	
	private int BestRandE = 0;
	private int LastRandE = 0;
	private int GamesPlayedRandE = 0;
	private double MeanRandE = 0;
	private int[] Last10RandE;
	
	private int BestRandH = 0;
	private int LastRandH = 0;
	private int GamesPlayedRandH = 0;
	private double MeanRandH = 0;
	private int[] Last10RandH;

	private String PlayerName;
	private static final int NUMOFGAMESRECORDED = 100;
	
	private ArrayList allLevels = new ArrayList();
	
	//TODO: Use Yiannis enums
	public enum statType {
		BEST, LAST, MEAN, GAMESPLAYED
	}

	public PersonalStats() {
	}

	public PersonalStats(String name) {
		this.PlayerName = name;
	}

	/**
	 * This method can change any statistic, given that you know the game mode
	 * and statistic you want to change.
	 * 
	 * @param m
	 * @param t
	 * @param score
	 */
	// TODO:
	public void setStats(Levels m, statType t, int score) {
		if (m == Levels.Addition) {
			switch (t) {
			case BEST:
				BestAdd = score;
				break;
			case LAST:
				LastAdd = score;
				break;
			case MEAN:
				MeanAdd = (double) score;
				break;
			case GAMESPLAYED:
				GamesPlayedAdd = score;
				break;
			}
		} else if (m == Levels.Subtraction) {
			switch (t) {
			case BEST:
				BestSub = score;
				break;
			case LAST:
				LastSub = score;
				break;
			case MEAN:
				MeanSub = (double) score;
				break;
			case GAMESPLAYED:
				GamesPlayedSub = score;
				break;
			}
		} else if (m == Levels.Multiplication) {
			switch (t) {
			case BEST:
				BestMul = score;
				break;
			case LAST:
				LastMul = score;
				break;
			case MEAN:
				MeanMul = (double) score;
				break;
			case GAMESPLAYED:
				GamesPlayedMul = score;
				break;
			}
		} else if (m == Levels.Division) {
			switch (t) {
			case BEST:
				BestDiv = score;
				break;
			case LAST:
				LastDiv = score;
				break;
			case MEAN:
				MeanDiv = (double) score;
				break;
			case GAMESPLAYED:
				GamesPlayedDiv = score;
				break;
			}
		} else if (m == Levels.Random) {
			switch (t) {
			case BEST:
				BestRandE = score;
			case LAST:
				LastRandE = score;
				break;
			case MEAN:
				MeanRandE = (double)score;
				break;
			case GAMESPLAYED:
				GamesPlayedRandE = score;
				break;
			}
		} else if (m == Levels.RandomHard) {
			switch (t) {
			case BEST:
				BestRandH = score;
			case LAST:
				LastRandH = score;
				break;
			case MEAN:
				MeanRandH = (double)score;
				break;
			case GAMESPLAYED:
				GamesPlayedRandH = score;
				break;
			}
		} else if (m == Levels.PractiseEasy) {
			switch (t) {
			case BEST:
				BestPracE = score;
				break;
			case LAST:
				LastPracE = score;
				break;
			case MEAN:
				MeanPracE = (double)score;
				break;
			case GAMESPLAYED:
				GamesPlayedPracE = score;
				break;
			}
		} else if (m == Levels.PractiseHard) {
			switch (t) {
			case BEST:
				BestPracH = score;
				break;
			case LAST:
				LastPracH = score;
				break;
			case MEAN:
				MeanPracH = (double)score;
				break;
			case GAMESPLAYED:
				GamesPlayedPracH = score;
				break;
			}
		}

	}

	/**
	 * This should be called after you finish a game. It stores the last game
	 * score into the array, then increments the number of games played, then
	 * calculates a new mean value.
	 * 
	 * @param m
	 */
	public void recordLastGame(Levels m, int Score) {
		checkBest(m, Score);
		incrementGamesPlayed(m);
		recordLastScore(m);
		newMean(m);
	}

	/**
	 * Check if the parameter score is better than the current best for a particular level.
	 * @param m
	 * @param Score
	 */
	public void checkBest(Levels m, int Score) {
		switch (m) {
		case Addition:
			if (Score > BestAdd) {
				BestAdd = Score;
			}
			break;
		case Subtraction:
			if (Score > BestSub) {
				BestSub = Score;
			}
			break;
		case Multiplication:
			if (Score > BestMul) {
				BestMul = Score;
			}
			break;
		case Division:
			if (Score > BestDiv) {
				BestDiv = Score;
			}
			break;
		case Random:
			if (Score > BestRandE) {
				BestRandE = Score;
			}
			break;
		case RandomHard:
			if (Score > BestRandH) {
				BestRandH = Score;
			}
			break;
		case PractiseHard:
			if (Score > BestPracH) {
				BestPracH = Score;
			}
			break;
		case PractiseEasy:
			if (Score > BestPracE) {
				BestPracE = Score;
			}
			break;
		}
	}

	/**
	 * This increases the gamesPlayed field for any Levels by 1.
	 * 
	 * @param m
	 *            the Levels you wish to increment.
	 */
	public void incrementGamesPlayed(Levels m) {
		switch (m) {
		case Addition:
			GamesPlayedAdd++;
			break;
		case Subtraction:
			GamesPlayedSub++;
			break;
		case Multiplication:
			GamesPlayedMul++;
			break;
		case Division:
			GamesPlayedDiv++;
			break;
		case Random:
			GamesPlayedRandE++;
			break;
		case RandomHard:
			GamesPlayedRandH++;
			break;
		case PractiseEasy:
			GamesPlayedPracE++;
			break;
		case PractiseHard:
			GamesPlayedPracH++;
			break;
		}
	}

	/**
	 * This gets the last score you got, and puts it into the Last10 array for
	 * whichever Levels you choose.
	 * 
	 * @param m
	 */
	public void recordLastScore(Levels m) {
		switch (m) {
		case Addition:
			Last10Add[GamesPlayedAdd] = LastAdd;
			break;
		case Subtraction:
			Last10Sub[GamesPlayedSub] = LastSub;
			break;
		case Multiplication:
			Last10Mul[GamesPlayedMul] = LastMul;
			break;
		case Division:
			Last10Div[GamesPlayedDiv] = LastDiv;
			break;
		case Random:
			Last10RandE[GamesPlayedRandE] = LastRandE;
			break;
		case RandomHard:
			Last10RandH[GamesPlayedRandH] = LastRandH;
			break;
		case PractiseEasy:
			Last10PracE[GamesPlayedPracE] = LastPracE;
			break;
		case PractiseHard:
			Last10PracH[GamesPlayedPracH] = LastPracH;
			break;
		}
	}

	/**
	 * Calculates the mean based on the number of games played and previous
	 * scores.
	 * 
	 * @param m
	 *            The Levels you wish to calculate the mean for.
	 */
	public void newMean(Levels m) {
		int total = 0;
		switch (m) {
		case Addition:
			if (GamesPlayedAdd > 0) {
				for (int i = 0; i < GamesPlayedAdd; i++) {
					total = total + Last10Add[i];
				}
				MeanAdd = ((double) total / (double) GamesPlayedAdd);
			}
			break;
		case Subtraction:
			if (GamesPlayedSub > 0) {
				for (int i = 0; i < GamesPlayedSub; i++) {
					total = total + Last10Sub[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedSub);
			}
			break;
		case Multiplication:
			if (GamesPlayedMul > 0) {
				for (int i = 0; i < GamesPlayedMul; i++) {
					total = total + Last10Mul[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedMul);
			}
			break;
		case Division:
			if (GamesPlayedDiv > 0) {
				for (int i = 0; i < GamesPlayedDiv; i++) {
					total = total + Last10Div[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedDiv);
			}
			break;
		case Random:
			if (GamesPlayedRandE > 0) {
				for (int i = 0; i < GamesPlayedRandE; i++) {
					total = total + Last10RandE[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedRandE);
			}
			break;
		case RandomHard:
			if (GamesPlayedRandH > 0) {
				for (int i = 0; i < GamesPlayedRandH; i++) {
					total = total + Last10RandH[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedRandH);
			}
			break;
		case PractiseEasy:
			if (GamesPlayedPracE > 0) {
				for (int i = 0; i < GamesPlayedPracE; i++) {
					total = total + Last10PracE[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedPracE);
			}
			break;
		case PractiseHard:
			if (GamesPlayedPracH > 0) {
				for (int i = 0; i < GamesPlayedPracH; i++) {
					total = total + Last10PracH[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedPracH);
			}
			break;
		}
	}

	public String getPlayerName() {
		return this.PlayerName;
	}

	// These getters and setters are only here for the prototype. They'll
	// probably be replaced in the final version.
	

}
