package Tatai.view.stats;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name="personalStats")
public class PersonalStats {
	private int BestAdd;
	private int LastAdd;
	private int GamesPlayedAdd;
	private double MeanAdd;
	private int[] ProgressAdd;

	private int BestSub;
	private int LastSub;
	private int GamesPlayedSub;
	private double MeanSub;
	private int[] ProgressSub;

	private int BestMul;
	private int LastMul;
	private int GamesPlayedMul;
	private double MeanMul;
	private int[] ProgressMul;

	private int BestDiv;
	private int LastDiv;
	private int GamesPlayedDiv;
	private double MeanDiv;
	private int[] ProgressDiv;

	private int BestHard;
	private int LastHard;
	private int GamesPlayedHard;
	private double MeanHard;
	private int[] ProgressHard;

	private String PlayerName;
	private static final int NUMOFGAMESRECORDED = 100;

	public enum gameMode {
		ADD, SUB, MUL, DIV, HARD
	}

	public enum statType {
		BEST, LAST, MEAN, GAMESPLAYED
	}

	public PersonalStats() {
	}

	public PersonalStats(String name) {

		this.PlayerName = name;

		this.BestAdd = 0;
		this.LastAdd = 0;
		this.GamesPlayedAdd = 0;
		this.MeanAdd = 0.0;
		this.ProgressAdd = new int[NUMOFGAMESRECORDED];

		this.BestSub = 0;
		this.LastSub = 0;
		this.GamesPlayedSub = 0;
		this.MeanSub = 0.0;
		this.ProgressSub = new int[NUMOFGAMESRECORDED];

		this.BestMul = 0;
		this.LastMul = 0;
		this.GamesPlayedMul = 0;
		this.MeanMul = 0.0;
		this.ProgressMul = new int[NUMOFGAMESRECORDED];

		this.BestDiv = 0;
		this.LastDiv = 0;
		this.GamesPlayedDiv = 0;
		this.MeanDiv = 0.0;
		this.ProgressDiv = new int[NUMOFGAMESRECORDED];

		this.BestHard = 0;
		this.LastHard = 0;
		this.GamesPlayedHard = 0;
		this.MeanHard = 0.0;
		this.ProgressHard = new int[NUMOFGAMESRECORDED];

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
	public void setStats(gameMode m, statType t, int score) {
		if (m == gameMode.ADD) {
			switch (t) {
			case BEST:
				BestAdd = score;
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
		} else if (m == gameMode.SUB) {
			switch (t) {
			case BEST:
				BestSub = score;
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
		} else if (m == gameMode.MUL) {
			switch (t) {
			case BEST:
				BestMul = score;
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
		} else if (m == gameMode.DIV) {
			switch (t) {
			case BEST:
				BestDiv = score;
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
		} else if (m == gameMode.HARD) {
			switch (t) {
			case BEST:
				BestHard = score;
			case LAST:
				LastHard = score;
				break;
			case MEAN:
				MeanHard = (double) score;
				break;
			case GAMESPLAYED:
				GamesPlayedHard = score;
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
	public void recordLastGame(gameMode m, int Score) {
		checkBest(m, Score);
		incrementGamesPlayed(m);
		recordLastScore(m);
		newMean(m);
	}

	public void checkBest(gameMode m, int Score) {
		switch (m) {
		case ADD:
			if (Score > BestAdd) {
				BestAdd = Score;
			}
			break;
		case SUB:
			if (Score > BestSub) {
				BestSub = Score;
			}
			break;
		case MUL:
			if (Score > BestMul) {
				BestMul = Score;
			}
			break;
		case DIV:
			if (Score > BestDiv) {
				BestDiv = Score;
			}
			break;
		case HARD:
			if (Score > BestHard) {
				BestHard = Score;
			}
			break;
		}
	}

	/**
	 * This increases the gamesPlayed field for any gameMode by 1.
	 * 
	 * @param m
	 *            the gameMode you wish to increment.
	 */
	public void incrementGamesPlayed(gameMode m) {
		switch (m) {
		case ADD:
			GamesPlayedAdd++;
			break;
		case SUB:
			GamesPlayedSub++;
			break;
		case MUL:
			GamesPlayedMul++;
			break;
		case DIV:
			GamesPlayedDiv++;
			break;
		case HARD:
			GamesPlayedHard++;
			break;
		}
	}

	/**
	 * This gets the last score you got, and puts it into the Progress array for
	 * whichever gameMode you choose.
	 * 
	 * @param m
	 */
	public void recordLastScore(gameMode m) {
		switch (m) {
		case ADD:
			ProgressAdd[GamesPlayedAdd] = LastAdd;
			break;
		case SUB:
			ProgressSub[GamesPlayedSub] = LastSub;
			break;
		case DIV:
			ProgressMul[GamesPlayedMul] = LastMul;
			break;
		case MUL:
			ProgressDiv[GamesPlayedDiv] = LastDiv;
			break;
		case HARD:
			ProgressHard[GamesPlayedHard] = LastHard;
			break;
		}
	}

	/**
	 * Calculates the mean based on the number of games played and previous
	 * scores.
	 * 
	 * @param m
	 *            The gameMode you wish to calculate the mean for.
	 */
	public void newMean(gameMode m) {
		int total = 0;
		switch (m) {
		case ADD:
			if (GamesPlayedAdd > 0) {
				for (int i = 0; i < GamesPlayedAdd; i++) {
					total = total + ProgressAdd[i];
				}
				MeanAdd = ((double) total / (double) GamesPlayedAdd);
			}
			break;
		case SUB:
			if (GamesPlayedSub > 0) {
				for (int i = 0; i < GamesPlayedSub; i++) {
					total = total + ProgressSub[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedSub);
			}
			break;
		case MUL:
			if (GamesPlayedMul > 0) {
				for (int i = 0; i < GamesPlayedMul; i++) {
					total = total + ProgressMul[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedMul);
			}
			break;
		case DIV:
			if (GamesPlayedDiv > 0) {
				for (int i = 0; i < GamesPlayedDiv; i++) {
					total = total + ProgressDiv[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedDiv);
			}
			break;
		case HARD:
			if (GamesPlayedHard > 0) {
				for (int i = 0; i < GamesPlayedHard; i++) {
					total = total + ProgressHard[i];
				}
				MeanSub = ((double) total / (double) GamesPlayedHard);
			}
			break;
		}
	}

	// These getters and setters are only here for the prototype. They'll
	// probably be replaced in the final version.
	public int getBestHard() {
		return BestHard;
	}

	public void setBestHard(int bestHard) {
		BestHard = bestHard;
	}

	public int getLastHard() {
		return LastHard;
	}

	public void setLastHard(int lastHard) {
		LastHard = lastHard;
	}

	public double getMeanHard() {
		return MeanHard;
	}

	public void setMeanHard(double score) {
		MeanHard = score;
	}

	public int getGamesPlayedHard() {
		return GamesPlayedHard;
	}

	public void setGamesPlayedHard(int gamesPlayedHard) {
		GamesPlayedHard = gamesPlayedHard;
	}

	public int getBestAdd() {
		return BestAdd;
	}

	public void setBestAdd(int bestAdd) {
		BestAdd = bestAdd;
	}

	public int getLastAdd() {
		return LastAdd;
	}

	public void setLastAdd(int lastAdd) {
		LastAdd = lastAdd;
	}

	public double getMeanAdd() {
		return MeanAdd;
	}

	public void setMeanAdd(double d) {
		MeanAdd = d;
	}

	public int getGamesPlayedAdd() {
		return GamesPlayedAdd;
	}

	public void setGamesPlayedAdd(int gamesPlayedAdd) {
		GamesPlayedAdd = gamesPlayedAdd;
	}

	public int getBestSub() {
		return BestSub;
	}

	public void setBestSub(int bestSub) {
		BestSub = bestSub;
	}

	public int getLastSub() {
		return LastSub;
	}

	public void setLastSub(int lastSub) {
		LastSub = lastSub;
	}

	public double getMeanSub() {
		return MeanSub;
	}

	public void setMeanSub(double meanSub) {
		MeanSub = meanSub;
	}

	public int getGamesPlayedSub() {
		return GamesPlayedSub;
	}

	public void setGamesPlayedSub(int gamesPlayedSub) {
		GamesPlayedSub = gamesPlayedSub;
	}

	public int getBestMul() {
		return BestMul;
	}

	public void setBestMul(int bestMul) {
		BestMul = bestMul;
	}

	public int getLastMul() {
		return LastMul;
	}

	public void setLastMul(int lastMul) {
		LastMul = lastMul;
	}

	public double getMeanMul() {
		return MeanMul;
	}

	public void setMeanMul(double meanMul) {
		MeanMul = meanMul;
	}

	public int getGamesPlayedMul() {
		return GamesPlayedMul;
	}

	public void setGamesPlayedMul(int gamesPlayedMul) {
		GamesPlayedMul = gamesPlayedMul;
	}

	public int getBestDiv() {
		return BestDiv;
	}

	public void setBestDiv(int bestDiv) {
		BestDiv = bestDiv;
	}

	public int getLastDiv() {
		return LastDiv;
	}

	public void setLastDiv(int lastDiv) {
		LastDiv = lastDiv;
	}

	public double getMeanDiv() {
		return MeanDiv;
	}

	public void setMeanDiv(double meanDiv) {
		MeanDiv = meanDiv;
	}

	public int getGamesPlayedDiv() {
		return GamesPlayedDiv;
	}

	public void setGamesPlayedDiv(int gamesPlayedDiv) {
		GamesPlayedDiv = gamesPlayedDiv;
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

}
