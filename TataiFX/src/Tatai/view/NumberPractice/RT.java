package Tatai.view.NumberPractice;

import Tatai.model.Recording;
import javafx.concurrent.Task;

public class RT extends Task<Recording> {

	@Override
	protected Recording call() throws Exception {
		Recording recording = new Recording();
		// Uncomment when testing on linex

		recording.record();
		return recording;
	}
	
	@Override
	protected void succeeded() {
		
	}
	
}
