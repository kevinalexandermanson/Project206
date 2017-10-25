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
	private int[] Last10Add = new int[NUMOFGAMESRECORDED];

	private int BestSub = 0;
	private int LastSub = 0;
	private int GamesPlayedSub = 0;
	private double MeanSub = 0;
	private int[] Last10Sub = new int[NUMOFGAMESRECORDED];

	private int BestMul = 0;
	private int LastMul = 0;
	private int GamesPlayedMul = 0;
	private double MeanMul = 0;
	private int[] Last10Mul = new int[NUMOFGAMESRECORDED];

	private int BestDiv = 0;
	private int LastDiv = 0;
	private int GamesPlayedDiv = 0;
	private double MeanDiv = 0;
	private int[] Last10Div = new int[NUMOFGAMESRECORDED];
	
	private int BestPracE = 0;
	private int LastPracE = 0;
	private int GamesPlayedPracE = 0;
	private double MeanPracE = 0;
	private int[] Last10PracE = new int[NUMOFGAMESRECORDED];

	private int BestPracH = 0;
	private int LastPracH = 0;
	private int GamesPlayedPracH = 0;
	private double MeanPracH = 0;
	private int[] Last10PracH = new int[NUMOFGAMESRECORDED];
	
	private int BestRandE = 0;
	private int LastRandE = 0;
	private int GamesPlayedRandE = 0;
	private double MeanRandE = 0;
	private int[] Last10RandE = new int[NUMOFGAMESRECORDED];
	
	private int BestRandH = 0;
	private int LastRandH = 0;
	private int GamesPlayedRandH = 0;
	private double MeanRandH = 0;
	private int[] Last10RandH = new int[NUMOFGAMESRECORDED];

	private String PlayerName;
	private static final int NUMOFGAMESRECORDED = 100;
	
	private ArrayList allLevels = new ArrayList();
	
	public enum statType {
		BEST, LAST, MEAN, GAMESPLAYED
	}

	public PersonalStats() {
	}

	//The int and double are just filler. For some reason the xml file will only save if the constructor modified the field with a parameter.
	public PersonalStats(String name) {
		this.PlayerName = name;
		this.resetScores();
		
		//System.out.println("Name is: " + this.PlayerName);
		//System.out.println("getPlayerName test: " + this.getPlayerName());
		//System.out.println("Best add is " + this.BestAdd);
		//System.out.println("Best add is " + this.getBestAdd());
	}

	public void resetScores() {
		this. BestAdd = 0;
		this. LastAdd = 0;
		this. GamesPlayedAdd = 0;
		this. MeanAdd = 0;
		this. Last10Add = new int[NUMOFGAMESRECORDED];

		this. BestSub = 0;
		this. LastSub = 0;
		this. GamesPlayedSub = 0;
		this. MeanSub = 0;
		this. Last10Sub = new int[NUMOFGAMESRECORDED];

		this. BestMul = 0;
		this. LastMul = 0;
		this. GamesPlayedMul = 0;
		this. MeanMul = 0;
		this. Last10Mul = new int[NUMOFGAMESRECORDED];

		this. BestDiv = 0;
		this. LastDiv = 0;
		this. GamesPlayedDiv = 0;
		this. MeanDiv = 0;
		this. Last10Div = new int[NUMOFGAMESRECORDED];
		
		this. BestPracE = 0;
		this. LastPracE = 0;
		this. GamesPlayedPracE = 0;
		this. MeanPracE = 0;
		this. Last10PracE = new int[NUMOFGAMESRECORDED];

		this. BestPracH = 0;
		this. LastPracH = 0;
		this. GamesPlayedPracH = 0;
		this. MeanPracH = 0;
		this. Last10PracH = new int[NUMOFGAMESRECORDED];
		
		this. BestRandE = 0;
		this. LastRandE = 0;
		this. GamesPlayedRandE = 0;
		this. MeanRandE = 0;
		this. Last10RandE = new int[NUMOFGAMESRECORDED];
		
		this. BestRandH = 0;
		this. LastRandH = 0;
		this. GamesPlayedRandH = 0;
		this. MeanRandH = 0;
		this. Last10RandH = new int[NUMOFGAMESRECORDED];
	}
	
	/**
	 * This should be called after you finish a game. It stores the last game
	 * score into the array, then increments the number of games played, then
	 * calculates a new mean value. NOTE: This save the data in the OBJECT, not
	 * the .xml. Saving the .xml occurs in another method, in the
	 * LoginController.class
	 * 
	 * @param m
	 */
	public void recordLastGame(String s, int Score) {
		Levels m = stringToLevels(s);// 0. Convert the string to a game mode enum.
		setLast(m, Score);			// 1. Update the 'last' score.
		checkBest(m, Score);		// 2. Check if the score beats the 'best' score. If so, update.
		recordLastScore(m);			// 4. Add the new score to the array of recorded scores.
		incrementGamesPlayed(m);	// 3. Update the number of games played.
		newMean(m);					// 5. Update the mean.
	}

	private Levels stringToLevels(String s) {
		switch (s) {
		case "+":
			return Levels.Addition;
		case "-":
			return Levels.Subtraction;
		case "*":
			return Levels.Multiplication;
		case "/":
			return Levels.Division;
		case "Random":
			return Levels.Random;
		case "RandomHard":
			return Levels.RandomHard;
		case "PractiseHard":
			return Levels.PractiseHard;
		case "PractiseEasy":
			return Levels.PractiseEasy;
		}
		return null;
	}

	/**
	 * See recordLastGame for details...
	 * @param m
	 * @param Score
	 */
	private void setLast(Levels m, int Score) {
		switch (m) {
		case Addition:
			LastAdd=Score;
			break;
		case Subtraction:
			LastSub=Score;
			break;
		case Multiplication:
			LastMul=Score;
			break;
		case Division:
			LastDiv=Score;
			break;
		case Random:
			LastRandE=Score;
			break;
		case RandomHard:
			LastRandH=Score;
			break;
		case PractiseHard:
			LastPracH=Score;
			break;
		case PractiseEasy:
			LastPracE=Score;
			break;
		}
		
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
				MeanMul = ((double) total / (double) GamesPlayedMul);
			}
			break;
		case Division:
			if (GamesPlayedDiv > 0) {
				for (int i = 0; i < GamesPlayedDiv; i++) {
					total = total + Last10Div[i];
				}
				MeanDiv = ((double) total / (double) GamesPlayedDiv);
			}
			break;
		case Random:
			if (GamesPlayedRandE > 0) {
				for (int i = 0; i < GamesPlayedRandE; i++) {
					total = total + Last10RandE[i];
				}
				MeanRandE = ((double) total / (double) GamesPlayedRandE);
			}
			break;
		case RandomHard:
			if (GamesPlayedRandH > 0) {
				for (int i = 0; i < GamesPlayedRandH; i++) {
					total = total + Last10RandH[i];
				}
				MeanRandH = ((double) total / (double) GamesPlayedRandH);
			}
			break;
		case PractiseEasy:
			if (GamesPlayedPracE > 0) {
				for (int i = 0; i < GamesPlayedPracE; i++) {
					total = total + Last10PracE[i];
				}
				MeanPracE = ((double) total / (double) GamesPlayedPracE);
			}
			break;
		case PractiseHard:
			if (GamesPlayedPracH > 0) {
				for (int i = 0; i < GamesPlayedPracH; i++) {
					total = total + Last10PracH[i];
				}
				MeanPracH = ((double) total / (double) GamesPlayedPracH);
			}
			break;
		}
	}

	public void setPlayerName(String name){
		this.PlayerName=name;
	}
	
	public String getPlayerName() {
		return this.PlayerName;
	}

	public int getBestAdd() {
		return BestAdd;
	}

	public int getLastAdd() {
		return LastAdd;
	}

	public int getGamesPlayedAdd() {
		return GamesPlayedAdd;
	}

	public double getMeanAdd() {
		return MeanAdd;
	}

	public int[] getLast10Add() {
		return Last10Add;
	}

	public int getBestSub() {
		return BestSub;
	}

	public int getLastSub() {
		return LastSub;
	}

	public int getGamesPlayedSub() {
		return GamesPlayedSub;
	}

	public double getMeanSub() {
		return MeanSub;
	}

	public int[] getLast10Sub() {
		return Last10Sub;
	}

	public int getBestMul() {
		return BestMul;
	}

	public int getLastMul() {
		return LastMul;
	}

	public int getGamesPlayedMul() {
		return GamesPlayedMul;
	}

	public double getMeanMul() {
		return MeanMul;
	}

	public int[] getLast10Mul() {
		return Last10Mul;
	}

	public int getBestDiv() {
		return BestDiv;
	}

	public int getLastDiv() {
		return LastDiv;
	}

	public int getGamesPlayedDiv() {
		return GamesPlayedDiv;
	}

	public double getMeanDiv() {
		return MeanDiv;
	}

	public int[] getLast10Div() {
		return Last10Div;
	}

	public int getBestPracE() {
		return BestPracE;
	}

	public int getLastPracE() {
		return LastPracE;
	}

	public int getGamesPlayedPracE() {
		return GamesPlayedPracE;
	}

	public double getMeanPracE() {
		return MeanPracE;
	}

	public int[] getLast10PracE() {
		return Last10PracE;
	}

	public int getBestPracH() {
		return BestPracH;
	}

	public int getLastPracH() {
		return LastPracH;
	}

	public int getGamesPlayedPracH() {
		return GamesPlayedPracH;
	}

	public double getMeanPracH() {
		return MeanPracH;
	}

	public int[] getLast10PracH() {
		return Last10PracH;
	}

	public int getBestRandE() {
		return BestRandE;
	}

	public int getLastRandE() {
		return LastRandE;
	}

	public int getGamesPlayedRandE() {
		return GamesPlayedRandE;
	}

	public double getMeanRandE() {
		return MeanRandE;
	}

	public int[] getLast10RandE() {
		return Last10RandE;
	}

	public int getBestRandH() {
		return BestRandH;
	}

	public int getLastRandH() {
		return LastRandH;
	}

	public int getGamesPlayedRandH() {
		return GamesPlayedRandH;
	}

	public double getMeanRandH() {
		return MeanRandH;
	}

	public int[] getLast10RandH() {
		return Last10RandH;
	}

	public ArrayList getAllLevels() {
		return allLevels;
	}

	public void setBestAdd(int bestAdd) {
		BestAdd = bestAdd;
	}

	public void setLastAdd(int lastAdd) {
		LastAdd = lastAdd;
	}

	public void setGamesPlayedAdd(int gamesPlayedAdd) {
		GamesPlayedAdd = gamesPlayedAdd;
	}

	public void setMeanAdd(double meanAdd) {
		MeanAdd = meanAdd;
	}

	public void setLast10Add(int[] last10Add) {
		Last10Add = last10Add;
	}

	public void setBestSub(int bestSub) {
		BestSub = bestSub;
	}

	public void setLastSub(int lastSub) {
		LastSub = lastSub;
	}

	public void setGamesPlayedSub(int gamesPlayedSub) {
		GamesPlayedSub = gamesPlayedSub;
	}

	public void setMeanSub(double meanSub) {
		MeanSub = meanSub;
	}

	public void setLast10Sub(int[] last10Sub) {
		Last10Sub = last10Sub;
	}

	public void setBestMul(int bestMul) {
		BestMul = bestMul;
	}

	public void setLastMul(int lastMul) {
		LastMul = lastMul;
	}

	public void setGamesPlayedMul(int gamesPlayedMul) {
		GamesPlayedMul = gamesPlayedMul;
	}

	public void setMeanMul(double meanMul) {
		MeanMul = meanMul;
	}

	public void setLast10Mul(int[] last10Mul) {
		Last10Mul = last10Mul;
	}

	public void setBestDiv(int bestDiv) {
		BestDiv = bestDiv;
	}

	public void setLastDiv(int lastDiv) {
		LastDiv = lastDiv;
	}

	public void setGamesPlayedDiv(int gamesPlayedDiv) {
		GamesPlayedDiv = gamesPlayedDiv;
	}

	public void setMeanDiv(double meanDiv) {
		MeanDiv = meanDiv;
	}

	public void setLast10Div(int[] last10Div) {
		Last10Div = last10Div;
	}

	public void setBestPracE(int bestPracE) {
		BestPracE = bestPracE;
	}

	public void setLastPracE(int lastPracE) {
		LastPracE = lastPracE;
	}

	public void setGamesPlayedPracE(int gamesPlayedPracE) {
		GamesPlayedPracE = gamesPlayedPracE;
	}

	public void setMeanPracE(double meanPracE) {
		MeanPracE = meanPracE;
	}

	public void setLast10PracE(int[] last10PracE) {
		Last10PracE = last10PracE;
	}

	public void setBestPracH(int bestPracH) {
		BestPracH = bestPracH;
	}

	public void setLastPracH(int lastPracH) {
		LastPracH = lastPracH;
	}

	public void setGamesPlayedPracH(int gamesPlayedPracH) {
		GamesPlayedPracH = gamesPlayedPracH;
	}

	public void setMeanPracH(double meanPracH) {
		MeanPracH = meanPracH;
	}

	public void setLast10PracH(int[] last10PracH) {
		Last10PracH = last10PracH;
	}

	public void setBestRandE(int bestRandE) {
		BestRandE = bestRandE;
	}

	public void setLastRandE(int lastRandE) {
		LastRandE = lastRandE;
	}

	public void setGamesPlayedRandE(int gamesPlayedRandE) {
		GamesPlayedRandE = gamesPlayedRandE;
	}

	public void setMeanRandE(double meanRandE) {
		MeanRandE = meanRandE;
	}

	public void setLast10RandE(int[] last10RandE) {
		Last10RandE = last10RandE;
	}

	public void setBestRandH(int bestRandH) {
		BestRandH = bestRandH;
	}

	public void setLastRandH(int lastRandH) {
		LastRandH = lastRandH;
	}

	public void setGamesPlayedRandH(int gamesPlayedRandH) {
		GamesPlayedRandH = gamesPlayedRandH;
	}

	public void setMeanRandH(double meanRandH) {
		MeanRandH = meanRandH;
	}

	public void setLast10RandH(int[] last10RandH) {
		Last10RandH = last10RandH;
	}

	public void setAllLevels(ArrayList allLevels) {
		this.allLevels = allLevels;
	}

	
	
	/**
	 * This method can change any statistic, given that you know the game mode
	 * and statistic you want to change.
	 * 
	 * @param m
	 * @param t
	 * @param score
	 */
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
}
