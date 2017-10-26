package Tatai.model.threads;

import com.jfoenix.controls.JFXButton;

import Tatai.model.AudioFeedBack;
import javafx.concurrent.Task;

/** Thread to handle pronunciation playback **/
public class PronunciationThread extends Task<Void> {

	private int currentNum;
	private JFXButton btnPronunciation;
	
	public PronunciationThread(JFXButton btnPronunciation, int currentNum) {
		this.btnPronunciation = btnPronunciation;
		this.currentNum = currentNum;
	}
		
	@Override
	protected Void call() throws Exception {
		AudioFeedBack.playFeedBackAudio(currentNum);
		return null;
	}

	@Override 
	protected void succeeded() {
		btnPronunciation.setDisable(false);
		super.succeeded();

	}
}
