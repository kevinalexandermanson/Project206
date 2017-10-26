package Tatai.model.threads;

import java.util.concurrent.ExecutionException;

import Tatai.model.Recording;
import javafx.concurrent.Task;

/** Thread to handle recording **/
public class RecordingThread extends Task<Recording> {

	private GUIUpdate task;
	
	public RecordingThread(GUIUpdate task) {
		this.task = task;
	}
	
	@Override
	protected Recording call() throws Exception {
		Recording recording = new Recording();
		recording.record();
		return recording;
	}
	
	@Override
	protected void succeeded() {
		Recording recording = null;
		try {
			recording = get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		task.updateGUI(recording);
	}

	
}
