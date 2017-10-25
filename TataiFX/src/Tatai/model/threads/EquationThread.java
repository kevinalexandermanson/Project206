package Tatai.model.threads;

import java.util.Map;

import Tatai.Levels.LevelInterface;
import Tatai.view.game.GameController;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

public class EquationThread extends Task<Void>{

	private Map<String, LevelInterface> map;
	private String level;
	private Label lblCurrentGameNumber;
	private Label lblNowPlaying;

	public EquationThread(Map<String, LevelInterface> map, String level, Label currentGameNumber, Label nowPlaying) {
		this.map = map;
		this.level = level;
		this.lblCurrentGameNumber = currentGameNumber;
		this.lblNowPlaying = nowPlaying;
	}

	protected Void call() throws Exception {
		// Calculates the apporpriate question depending on the level
		map.get(level).calculate();
		return null;
	}

	@Override 
	protected void succeeded() {
		lblCurrentGameNumber.setText(map.get(level).getEquation());
		lblNowPlaying.setText(map.get(level).getLabel());
	}


}
