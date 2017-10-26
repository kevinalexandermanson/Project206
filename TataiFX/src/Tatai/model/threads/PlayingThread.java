package Tatai.model.threads;

import com.jfoenix.controls.JFXButton;

import Tatai.model.Recording;
import javafx.concurrent.Task;

/** Thread to handle recording play back **/
public class PlayingThread extends Task<Void>  {

	private JFXButton btnPlayRecording;
	
	public PlayingThread(JFXButton btnPlayRecording) {
		this.btnPlayRecording = btnPlayRecording;
	}
	
	@Override
	protected Void call() throws Exception {
		Recording recording = new Recording();
		recording.playRecording();
		return null;
	}

	@Override 
	protected void succeeded() {
		btnPlayRecording.setDisable(false);
		super.succeeded();
		
	}
}
