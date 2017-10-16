package Tatai.view.stats;

import javax.xml.bind.annotation.XmlRootElement;




//@XmlRootElement(name="personalStats")
public class PersonalStats {
	private int BestAdd;
	private int LastAdd;
	private int MeanAdd;
	private int GamesPlayedAdd;
	
	private int BestSub;
	private int LastSub;
	private int MeanSub;
	private int GamesPlayedSub;
	
	private int BestMul;
	private int LastMul;
	private int MeanMul;
	private int GamesPlayedMul;
	
	private int BestDiv;
	private int LastDiv;
	private int MeanDiv;
	private int GamesPlayedDiv;
	
	private int BestHard;
	private int LastHard;
	private int MeanHard;
	private int GamesPlayedHard;
	
	private String PlayerName;
	
	public enum gameMode {ADD, SUB, MUL, DIV, HARD}

	public enum statType {BEST, LAST, MEAN, GAMESPLAYED}
	
	public PersonalStats(){}
	
	public PersonalStats(String name) {
		
		this.PlayerName=name;
		
		this.BestAdd=0;
		this.LastAdd=0;
		this.MeanAdd=0;
		this.GamesPlayedAdd=0;
		
		this.BestSub=0;
		this.LastSub=0;
		this.MeanSub=0;
		this.GamesPlayedSub=0;
		
		this.BestMul=0;
		this.LastMul=0;
		this.MeanMul=0;
		this.GamesPlayedMul=0;
		
		this.BestDiv=0;
		this.LastDiv=0;
		this.MeanDiv=0;
		this.GamesPlayedDiv=0;
		
		this.BestHard=0;
		this.LastHard=0;
		this.MeanHard=0;
		this.GamesPlayedHard=0;
		
	}

	/**
	 * This method can change any statistic, given that you know the game mode
	 * and statistic you want to change.
	 * @param m 
	 * @param t
	 * @param score
	 */
	// TODO:
	public void setStats(gameMode m, statType t, int score) {
		if (m == gameMode.ADD) {
			switch (t) {
			case BEST:
				setBestAdd(score);
				break;

			case LAST:
				setLastAdd(score);
				break;

			case MEAN:
				setMeanAdd(score);
				break;

			case GAMESPLAYED:
				setGamesPlayedAdd(score);
				break;
			}
		} else if (m == gameMode.SUB) {
			switch (t) {
			case BEST:
				setBestSub(score);
				break;

			case LAST:
				setLastSub(score);
				break;

			case MEAN:
				setMeanSub(score);
				break;

			case GAMESPLAYED:
				setGamesPlayedSub(score);
				break;
			}
		} else if (m == gameMode.MUL) {
			switch (t) {
			case BEST:
				setBestMul(score);
				break;

			case LAST:
				setLastMul(score);
				break;

			case MEAN:
				setMeanMul(score);
				break;

			case GAMESPLAYED:
				setGamesPlayedMul(score);
				break;
			}
		} else if (m == gameMode.DIV) {
			switch (t) {
			case BEST:
				setBestDiv(score);
				break;

			case LAST:
				setLastDiv(score);
				break;

			case MEAN:
				setMeanDiv(score);
				break;

			case GAMESPLAYED:
				setGamesPlayedDiv(score);
				break;
			}
		} else if (m == gameMode.HARD) {
			switch (t) {
			case BEST:
				setBestHard(score);
				break;

			case LAST:
				setLastHard(score);
				break;

			case MEAN:
				setMeanHard(score);
				break;

			case GAMESPLAYED:
				setGamesPlayedHard(score);
				break;
			}
		}

	}
	
	
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

	public int getMeanHard() {
		return MeanHard;
	}

	public void setMeanHard(int meanHard) {
		MeanHard = meanHard;
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


	public int getMeanAdd() {
		return MeanAdd;
	}


	public void setMeanAdd(int meanAdd) {
		MeanAdd = meanAdd;
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


	public int getMeanSub() {
		return MeanSub;
	}


	public void setMeanSub(int meanSub) {
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


	public int getMeanMul() {
		return MeanMul;
	}


	public void setMeanMul(int meanMul) {
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


	public int getMeanDiv() {
		return MeanDiv;
	}


	public void setMeanDiv(int meanDiv) {
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
