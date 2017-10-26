package Tatai.model.threads;

import Tatai.model.Recording;

/**
 * Interface to provide method signature that the controllers must 
 * implement to update the GUI when the recording is finished
 *
 */
public interface GUIUpdate {
	
	public void updateGUI(Recording recording);
}
